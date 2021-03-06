package com.example.android.datafrominternet

import android.widget.EditText
import android.widget.TextView
import com.example.android.datafrominternet.utilities.NetworkUtils
import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class MainActivityTests {
    val activity = Robolectric.setupActivity(MainActivity::class.java)!!

    @Test
    fun searchBoxHasHintMessage() {
        val searchBoxEditText = activity.findViewById(R.id.et_search_box) as EditText
        val context = RuntimeEnvironment.application
        assertEquals(searchBoxEditText.hint, context.getString(R.string.query_hint))
    }

    @Test
    fun urlDisplayHasDefaultMessage() {
        val urlDisplayTextView = activity.findViewById(R.id.tv_url_display) as TextView
        val context = RuntimeEnvironment.application
        assertEquals(urlDisplayTextView.text, context.getString(R.string.url_display))
    }

    @Test
    fun searchResultHasDefaultMessage() {
        val searchResultTextView = activity.findViewById(R.id.tv_github_search_results_json) as TextView
        val context = RuntimeEnvironment.application
        assertEquals(searchResultTextView.text, context.getString(R.string.query_prompt))
    }

    @Test
    fun onCreateMenuOptionReturnsTrue() {
        val menu = shadowOf(activity).optionsMenu
        assertEquals(menu.size(), 1)
    }

    @Test
    fun onMenuOptionItemClicked() {
        val searchMenuItem = shadowOf(activity).optionsMenu.findItem(R.id.action_search)
        assertEquals(searchMenuItem.isVisible, true)
        assertEquals(activity.onOptionsItemSelected(searchMenuItem), true)
        assertEquals(activity.onOptionsItemSelected(null), false)
    }

    @Test
    fun urlDisplayTextViewIsSetWhenSearchIsClicked() {
        setupSearchBox()

        val searchMenuItem = shadowOf(activity).optionsMenu.findItem(R.id.action_search)
        activity.onOptionsItemSelected(searchMenuItem)

        val urlDisplayTextView = activity.findViewById(R.id.tv_url_display) as TextView
        assertEquals(urlDisplayTextView.text, NetworkUtils.buildUrl("android").toString())
    }

    @Test
    fun searchResultsTextViewIsSetWhenSearchButtonIsClicked() {
        setupSearchBox()

        val condition = "this is the response from url"
        activity.getResponseFromHttpUrl = { url ->
            condition
        }

        val searchMenuItem = shadowOf(activity).optionsMenu.findItem(R.id.action_search)
        activity.onOptionsItemSelected(searchMenuItem)

        val searchResultsTextView = activity.findViewById(R.id.tv_github_search_results_json) as TextView
        assertTrue(searchResultsTextView.isShown)
        assertEquals(searchResultsTextView.text, condition)

        val errorTextView = activity.findViewById(R.id.tv_error_message_display) as TextView
        assertFalse(errorTextView.isShown)

        activity.getResponseFromHttpUrl = { _ ->
            null
        }
        activity.onOptionsItemSelected(searchMenuItem)

        assertFalse(searchResultsTextView.isShown)

        val context = RuntimeEnvironment.application
        assertTrue(errorTextView.isShown)
        assertEquals(errorTextView.text, context.getString(R.string.error_message))
    }

    @Test
    fun loadingIndicatorToggles() {
        setupSearchBox()

        val progressBar = activity.findViewById(R.id.pb_loading_indicator)
        assertFalse(progressBar.isShown)

        activity.getResponseFromHttpUrl = { url ->
            assertTrue(progressBar.isShown)
            null
        }

        val searchMenuItem = shadowOf(activity).optionsMenu.findItem(R.id.action_search)
        activity.onOptionsItemSelected(searchMenuItem)

        assertFalse(progressBar.isShown)
    }

    private fun setupSearchBox() {
        val query = "android"
        val searchBoxEditText = activity.findViewById(R.id.et_search_box) as EditText
        searchBoxEditText.setText(query)
    }
}
