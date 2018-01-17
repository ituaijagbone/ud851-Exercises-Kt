package com.example.android.datafrominternet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.example.android.datafrominternet.utilities.NetworkUtils

class MainActivity : AppCompatActivity() {
    private lateinit var mSearchBoxEditText: EditText
    private var mUrlDisplayTextView: TextView? = null
    private var mSearchResultsTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSearchBoxEditText = findViewById(R.id.et_search_box) as EditText
        mUrlDisplayTextView = findViewById(R.id.tv_url_display) as TextView
        mSearchResultsTextView = findViewById(R.id.tv_github_search_results_json) as TextView
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu!!)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when {
            item?.itemId == R.id.action_search -> {
                makeGithubSearchQuery()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun makeGithubSearchQuery() {
        val request = NetworkUtils.buildUrl(mSearchBoxEditText.text.toString()) ?: return
        mUrlDisplayTextView?.text = request.toString()
        val response = NetworkUtils.getResponseFromHttpUrl(request)
        mSearchResultsTextView?.text = response
    }
}
