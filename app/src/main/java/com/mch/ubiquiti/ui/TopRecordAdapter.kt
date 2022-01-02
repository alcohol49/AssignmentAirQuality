package com.mch.ubiquiti.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mch.ubiquiti.data.Record

class TopRecordAdapter : ListAdapter<Record, TopRecordViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRecordViewHolder {
        return TopRecordViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TopRecordViewHolder, position: Int) {
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
