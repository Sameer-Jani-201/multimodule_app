package com.bookmyshow.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bookmyshow.assignment.di.DaggerAppComponentProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}