/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.explicitintent

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var mNameEntry: EditText
    private lateinit var mDoSomethingCoolButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDoSomethingCoolButton = findViewById(R.id.b_do_something_cool) as Button
        mNameEntry = findViewById(R.id.et_text_entry) as EditText

        mDoSomethingCoolButton.setOnClickListener {
            val context = this@MainActivity
            val destinationActivity = ChildActivity::class.java
            val intent = Intent(context, destinationActivity)
            intent.putExtra(Intent.EXTRA_TEXT, mNameEntry.text.toString())
            startActivity(intent)
        }
    }
}