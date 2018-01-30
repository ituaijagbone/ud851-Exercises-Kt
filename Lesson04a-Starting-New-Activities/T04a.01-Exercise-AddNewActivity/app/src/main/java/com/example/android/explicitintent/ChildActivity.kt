package com.example.android.explicitintent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ChildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)
    }
}
