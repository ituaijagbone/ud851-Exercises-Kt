package com.example.android.datafrominternet

import android.widget.EditText
import android.widget.TextView
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class MainActivityTests {
    @Test
    fun searchBoxHasHintMessage() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val searchBoxEditText = activity.findViewById(R.id.et_search_box) as EditText
        val context = RuntimeEnvironment.application
        assertEquals(searchBoxEditText.hint, context.getString(R.string.query_hint))
    }

    @Test
    fun urlDisplayHasDefaultMessage() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val urlDisplayTextView = activity.findViewById(R.id.tv_url_display) as TextView
        val context = RuntimeEnvironment.application
        assertEquals(urlDisplayTextView.text, context.getString(R.string.url_display))
    }

    @Test
    fun searchResultHasDefaultMessage() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val searchResultTextView = activity.findViewById(R.id.tv_github_search_results_json) as TextView
        val context = RuntimeEnvironment.application
        assertEquals(searchResultTextView.text, context.getString(R.string.query_prompt))
    }
}
