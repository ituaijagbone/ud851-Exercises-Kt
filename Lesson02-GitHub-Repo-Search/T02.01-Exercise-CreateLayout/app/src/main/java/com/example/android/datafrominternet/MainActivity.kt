package com.example.android.datafrominternet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var mSearchBoxEditText: EditText? = null
    private var mUrlDisplayTextView: TextView? = null
    private var mSearchResultsTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUrlDisplayTextView = findViewById(R.id.et_search_box) as EditText
        mUrlDisplayTextView = findViewById(R.id.tv_url_display) as TextView
        mSearchResultsTextView = findViewById(R.id.tv_github_search_results_json) as TextView
    }
}
