package com.mch.ubiquiti.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mch.ubiquiti.R
import com.mch.ubiquiti.data.Record

class RecyclerViewViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_holder, parent, false)
) {

    private val adapter = RecordCardAdapter()


    init {
        itemView.findViewById<RecyclerView>(R.id.recycler_view).also {
            it.adapter = adapter
        }
    }

    fun onBind(list: List<Record>?) {
        adapter.submitList(list)
    }
}