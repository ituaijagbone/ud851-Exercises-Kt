package com.example.android.datafrominternet

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.android.datafrominternet.utilities.NetworkUtils
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    var getResponseFromHttpUrl: (URL) -> String? = NetworkUtils::getResponseFromHttpUrl

    private lateinit var mSearchBoxEditText: EditText
    private lateinit var mUrlDisplayTextView: TextView
    private lateinit var mSearchResultsTextView: TextView
    private lateinit var mErrorMessageTextView: TextView
    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSearchBoxEditText = findViewById(R.id.et_search_box) as EditText
        mUrlDisplayTextView = findViewById(R.id.tv_url_display) as TextView
        mSearchResultsTextView = findViewById(R.id.tv_github_search_results_json) as TextView
        mErrorMessageTextView = findViewById(R.id.tv_error_message_display) as TextView
        mProgressBar = findViewById(R.id.pb_loading_indicator) as ProgressBar
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
        val request = NetworkUtils.buildUrl(mSearchBoxEditText.text.toString())
        mUrlDisplayTextView.text = request?.toString()
        GithubQueryTask().execute(request)
    }

    private fun showJsonDataView() {
        mSearchResultsTextView.visibility = View.VISIBLE;
        mErrorMessageTextView.visibility = View.INVISIBLE;
    }

    private fun showErrorMessage() {
        mSearchResultsTextView.visibility = View.INVISIBLE;
        mErrorMessageTextView.visibility = View.VISIBLE;
    }

    private inner class GithubQueryTask: AsyncTask<URL?, Unit, String?>() {
        override fun onPreExecute() {
            mProgressBar.visibility = View.VISIBLE
        }
        override fun doInBackground(vararg params: URL?): String? {
            val url = params[0] ?: return null
            return try {
                getResponseFromHttpUrl(url)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        override fun onPostExecute(result: String?) {
            mProgressBar.visibility = View.INVISIBLE
            if (result != null && result.isNotEmpty()) {
                mSearchResultsTextView.text = result
                showJsonDataView()
            } else {
                showErrorMessage()
            }
        }

    }
}
