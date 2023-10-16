package com.hugo.trytospeak.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ActivityUtils
import com.hugo.trytospeak.utils.RouterTable

@SuppressLint("CustomSplashScreen")
@Route(path = RouterTable.PATH_SET_UP_ACTIVITY)
class SetupActivity : BaseUIActivity() {

    companion object {
        const val TAG = "SetupActivity"
        const val DEBUG = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().build(RouterTable.PATH_SPLASH_ACTIVITY).navigation()
        ActivityUtils.finishActivity(this)
    }
}