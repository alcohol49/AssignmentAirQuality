package com.mch.ubiquiti.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mch.ubiquiti.data.Record

class RecordCardAdapter : ListAdapter<Record, RecordCardViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordCardViewHolder {
        return RecordCardViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecordCardViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem.siteID == newItem.siteID
            }

            override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
                return oldItem == newItem
            }
        }
    }
}
