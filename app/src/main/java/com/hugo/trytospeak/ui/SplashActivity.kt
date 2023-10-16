package com.hugo.trytospeak.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ActivityUtils
import com.hugo.trytospeak.databinding.AndroidSplashBinding
import com.hugo.trytospeak.utils.RouterTable

@Route(path = RouterTable.PATH_SPLASH_ACTIVITY)
class SplashActivity : BaseUIActivity() {

    companion object {
        const val TAG = "SplashActivity"
        const val DEBUG = false
    }

    private lateinit var viewBinding: AndroidSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = AndroidSplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.countDown.setOnClickListener {
            viewBinding.countDown.reset()
            navHome()
        }
        viewBinding.countDown.countDown(8) {
            viewBinding.countDown.reset()
            navHome()
        }
    }

    private fun navHome() {
        ARouter.getInstance().build(RouterTable.PATH_MAIN_ACTIVITY).navigation()
        ActivityUtils.finishActivity(this)
    }
}