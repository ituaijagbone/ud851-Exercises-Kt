package com.example.android.explicitintent

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
}