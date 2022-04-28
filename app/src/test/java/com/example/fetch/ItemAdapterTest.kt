package com.example.fetch

import android.content.Context
import com.example.fetch.adapter.ItemAdapter
import com.example.fetch.data.myDataItem
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock

class ItemAdapterTest {
    private val context = mock(Context::class.java)


    /** Test the Adapter function for itemCount
     *  */

    @Test
    fun test_adapter_size() {
        val data = listOf(
            myDataItem(1, 1, "item 1"),
            myDataItem(2, 2, "item 2"),
            myDataItem(3, 3, ""),
            myDataItem(4, 4, "item 4"),
            myDataItem(5, 5, "")
        )
        val adapter = ItemAdapter(context, data)

        assertEquals("itemAdapter is no correct size", data.size, adapter.itemCount)
    }

}