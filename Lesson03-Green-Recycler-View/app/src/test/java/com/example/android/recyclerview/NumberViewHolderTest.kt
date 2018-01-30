package com.example.android.recyclerview

import android.view.LayoutInflater
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class NumberViewHolderTest {
    @Test
    fun onClickListener() {
        var actual: Int? = null
        class MockL: ListItemClickListener {
            override fun onListItemClick(itemPosition: Int) {
                actual = itemPosition
            }
        }
        val mockL = MockL()
        val context = RuntimeEnvironment.application
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.number_list_item, null, false)
        val viewHolder = GreenAdapter.NumberViewHolder(view, mockL)
        viewHolder.onClick(null)
        Assert.assertNotNull(actual)
    }
}