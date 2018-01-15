package com.example.android.favoritetoys

import android.widget.TextView
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTests {
    @Test
    fun toyListTextViewIsSetOnCreate() {
        val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        val textView = mainActivity.findViewById(R.id.tv_toy_names) as TextView
        assertEquals(textView.text, ToyBox.toyNames.joinToString("\n\n\n"))
    }

}