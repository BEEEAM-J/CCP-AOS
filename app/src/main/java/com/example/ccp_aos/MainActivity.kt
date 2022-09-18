package com.example.ccp_aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val str = "Sun Sep 18 11:20:09 UTC 2022 There was an unexpected error (type=Not Found, status=404) No message available"

        check.setOnClickListener(){
            content.setText(str)
        }



    }
}