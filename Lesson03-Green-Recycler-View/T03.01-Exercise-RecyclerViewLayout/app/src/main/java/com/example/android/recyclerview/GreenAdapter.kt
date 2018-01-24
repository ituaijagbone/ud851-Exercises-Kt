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

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class GreenAdapter(private val itemCount: Int) :
        RecyclerView.Adapter<GreenAdapter.NumberViewHolder>() {
    override fun getItemCount() = itemCount

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NumberViewHolder {
        val context = viewGroup.context
        val layoutIdForListItem = R.layout.number_list_item
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately)

        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        Log.d(TAG, "#" + position)
        holder.bind(position)
    }

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var listItemNumberView: TextView = itemView.findViewById(R.id.tv_item_number) as TextView

        fun bind(listIndex: Int) {
            listItemNumberView.text = listIndex.toString()
        }
    }

    companion object {
        private val TAG = GreenAdapter::class.java.simpleName
    }
}
