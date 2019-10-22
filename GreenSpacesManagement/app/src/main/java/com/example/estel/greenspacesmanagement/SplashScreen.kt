package com.example.estel.greenspacesmanagement

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.estel.greenspacesmanagement.view.ListMaintenanceTasks

class SplashScreen: AppCompatActivity() {

    //delay between the splash screen and the main activity
    private val splashTimeOut: Long = 5000

    private val mRunnable: Runnable = Runnable {
        val intent = Intent(this, ListMaintenanceTasks::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(mRunnable,splashTimeOut)
    }

}