package com.hugo.trytospeak.ui

import android.Manifest
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.hugo.lib.speech.chatsdk.SpeechManager
import com.hugo.trytospeak.R
import com.hugo.trytospeak.databinding.ActivityMainBinding
import com.hugo.trytospeak.utils.RouterTable
import com.hugo.trytospeak.utils.SpeechData
import com.hugo.trytospeak.utils.SpeechLiveData
import com.hugo.trytospeak.utils.SpeechLiveData.Companion.NLP_RESULT
import com.hugo.trytospeak.utils.SpeechLiveData.Companion.RECOGNIZE_RESULT
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

@Route(path = RouterTable.PATH_MAIN_ACTIVITY)
class MainActivity : BaseUIActivity() {

    companion object {
        const val TAG = "MainActivity"
        const val DEBUG = false
    }

    private lateinit var viewBinding: ActivityMainBinding
    private var subscribe: Disposable? = null

    private var curTag = ""

    private val fragmentsMap = mapOf(
        "map" to MapFragment(),
        "web" to AgentWebFragment(),
        "game" to GameFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        attachFragment("map")
        initPermission()
        val block = bounceBack(TimeUnit.SECONDS.toMillis(2L)) {
            var tag = curTag
            try {
                when (it.key) {
                    RECOGNIZE_RESULT -> {
                        tag = when (it.value[0]) {
                            "地图"   -> "map"
                            "浏览器" -> "web"
                            "游戏"   -> "game"
                            else -> curTag
                        }
                    }
                    NLP_RESULT -> {

                    }
                    else -> {}
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            attachFragment(tag)
        }
        SpeechLiveData.get().observe(this) { block(it) }
    }

    private fun attachFragment(tag: String) {
        if (tag in fragmentsMap && tag != curTag) supportFragmentManager.apply {
            try {
                // 如果已经存在fragment, 则需要先remove掉旧的(为了方便, 我们只选择最简单的方式处理)
                val trans = beginTransaction()
                findFragmentByTag(curTag)?.let { trans.remove(it) }
                trans.add(R.id.anchor_main, fragmentsMap[tag]!!, tag)
                trans.commitNow()
                curTag = tag
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun initPermission() {
        subscribe = RxPermissions(this).request(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE
        ).subscribe { granted ->
            if (granted) {
                Toast.makeText(this, "获取权限成功", Toast.LENGTH_SHORT).show()
                SpeechManager.CreateInstance(applicationContext)
            } else {
                Toast.makeText(this, "请先获取权限后使用", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bounceBack(duration: Long, block: (SpeechData)->Unit): (SpeechData)->Unit {
        var base = SystemClock.uptimeMillis()
        return {
            val now = SystemClock.uptimeMillis()
            if (base + duration < now) {
                block(it)
                base = now
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe?.dispose()
        SpeechManager.getInstance().onDestroy()
    }
}