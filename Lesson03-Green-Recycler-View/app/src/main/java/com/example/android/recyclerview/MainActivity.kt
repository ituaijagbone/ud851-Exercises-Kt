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
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity(), ListItemClickListener {

    private var mToast: Toast? = null
    private lateinit var mAdapter: GreenAdapter
    private lateinit var mNumbersList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNumbersList = findViewById(R.id.rv_numbers) as RecyclerView

        val layoutManager = LinearLayoutManager(this)
        mNumbersList.layoutManager = layoutManager

        mNumbersList.setHasFixedSize(true)

        mAdapter = GreenAdapter(NUM_LIST_ITEMS, this)

        mNumbersList.adapter = mAdapter
    }

    override fun onListItemClick(itemPosition: Int) {
        mToast?.cancel()
        mToast = Toast.makeText(this, "Item #$itemPosition clicked.", Toast.LENGTH_LONG)
        mToast?.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_refresh -> {
                mAdapter = GreenAdapter(NUM_LIST_ITEMS, this)
                mNumbersList.adapter = mAdapter
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        private val NUM_LIST_ITEMS = 100
    }
}
