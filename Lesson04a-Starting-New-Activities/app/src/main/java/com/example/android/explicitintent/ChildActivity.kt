package com.example.android.explicitintent

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class ChildActivity : AppCompatActivity() {
    private lateinit var mDisplayText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        mDisplayText = findViewById(R.id.tv_display) as TextView

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            mDisplayText.text = intent.getStringExtra(Intent.EXTRA_TEXT)
        }
    }
}
