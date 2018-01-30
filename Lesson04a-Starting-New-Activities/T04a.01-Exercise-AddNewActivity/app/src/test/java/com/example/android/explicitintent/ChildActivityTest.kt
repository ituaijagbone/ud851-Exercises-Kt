package com.example.android.explicitintent

import android.content.Intent
import android.widget.TextView
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ChildActivityTest {
    @Test
    fun displayTextViewHasDefaultText() {
        val activity = Robolectric.setupActivity(ChildActivity::class.java)
        val displayTextView = activity.findViewById(R.id.tv_display) as TextView
        val context = RuntimeEnvironment.application
        Assert.assertEquals(displayTextView.text, context.getString(R.string.tv_display_default))
    }

    @Test
    fun displayTextViewIsUpdatedWhenActivityHasExtraIntent() {
        val intent = Intent()
        val expected = "Hello World!"
        intent.putExtra(Intent.EXTRA_TEXT, expected)
        val activity = Robolectric.buildActivity(ChildActivity::class.java, intent)
                .create()
                .get()
        val displayTextView = activity.findViewById(R.id.tv_display) as TextView

        Assert.assertEquals(expected, displayTextView.text)
    }
}