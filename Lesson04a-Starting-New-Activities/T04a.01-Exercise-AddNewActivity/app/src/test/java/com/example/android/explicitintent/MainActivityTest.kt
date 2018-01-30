package com.example.android.explicitintent

import android.content.Intent
import android.widget.EditText
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    @Test
    fun doSomethingButtonShouldStartChildActivity() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val editTextView = activity.findViewById(R.id.et_text_entry) as EditText
        val expectedText = "Hello World"
        editTextView.setText(expectedText)
        activity.findViewById(R.id.b_do_something_cool).performClick()

        val expectedIntent = Intent(activity, ChildActivity::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        Assert.assertEquals(expectedIntent.component, actualIntent.component)
        Assert.assertEquals(expectedText, actualIntent.getStringExtra(Intent.EXTRA_TEXT))
    }

}