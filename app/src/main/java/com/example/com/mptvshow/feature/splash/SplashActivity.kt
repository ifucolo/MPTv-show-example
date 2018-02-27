package com.example.com.mptvshow.feature.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.com.mptvshow.R
import com.example.com.mptvshow.feature.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setHandler()
    }

    fun setHandler() {
        Handler().postDelayed({
            startActivity(MainActivity.launchIntent(this@SplashActivity))
        }
        , 700)
    }
}
