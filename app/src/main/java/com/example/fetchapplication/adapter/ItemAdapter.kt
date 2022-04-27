package com.example.fetchapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchapplication.R
import com.example.fetchapplication.data.myDataItem

class ItemAdapter(private val context: Context,
                  private val dataset: List<myDataItem>
                  ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewListId: TextView = view.findViewById(R.id.item_listId)
        val textViewName: TextView = view.findViewById(R.id.item_name)
        val textViewId: TextView = view.findViewById(R.id.item_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val resources = context?.resources
        val item = dataset[position]

        holder.textViewListId.text = resources?.getString(R.string.listId, item.listId.toString())
        holder.textViewName.text = resources?.getString(R.string.name, item.name)
        holder.textViewId.text = resources?.getString(R.string.id, item.id.toString())
    }

    override fun getItemCount() = dataset.size

}