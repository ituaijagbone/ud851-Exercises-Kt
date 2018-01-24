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
package com.example.android.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var mAdapter: GreenAdapter? = null
    private var mNumbersList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNumbersList = findViewById(R.id.rv_numbers) as RecyclerView

        val layoutManager = LinearLayoutManager(this)
        mNumbersList!!.setLayoutManager(layoutManager)

        mNumbersList!!.setHasFixedSize(true)

        mAdapter = GreenAdapter(NUM_LIST_ITEMS)

        mNumbersList!!.setAdapter(mAdapter)
    }

    companion object {

        private val NUM_LIST_ITEMS = 100
    }
}
