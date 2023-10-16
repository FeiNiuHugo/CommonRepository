package com.hugo.trytospeak

import android.app.Application
import android.content.Intent
import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.hugo.trytospeak.components.service.WebService
import com.just.agentweb.AgentWebCompat
import com.queue.library.GlobalQueue
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        initARouter()
        AgentWebCompat.setDataDirectorySuffix(this)
        GlobalQueue.getMainQueue().postRunnableInIdleRunning {
            try {
                startService(Intent(this@App, WebService::class.java))
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}