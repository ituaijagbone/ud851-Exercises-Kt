package com.example.android.datafrominternet

import com.example.android.datafrominternet.utilities.NetworkUtils
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NetworkUtilsTests {
    @Test
    fun buildUrlReturnUrl() {
        val condition = NetworkUtils.buildUrl("android")?.toString()?.contains("android")!!
            assertTrue(condition)
    }
}