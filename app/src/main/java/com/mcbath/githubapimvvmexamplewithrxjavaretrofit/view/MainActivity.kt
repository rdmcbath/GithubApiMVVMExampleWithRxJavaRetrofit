package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcbath.githubapimvvmexamplewithrxjavaretrofit.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val ft =  supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, MainFragment())
                    ft.commit()
        }
    }
}