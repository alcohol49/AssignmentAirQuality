package com.mch.ubiquiti.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mch.ubiquiti.R
import com.mch.ubiquiti.data.Record

class RecordCardViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_record_card, parent, false)
) {

    private val idView = itemView.findViewById<TextView>(R.id.site_id)
    private val nameView = itemView.findViewById<TextView>(R.id.site_name)
    private val countryView = itemView.findViewById<TextView>(R.id.country)
    private val pm25View = itemView.findViewById<TextView>(R.id.pm25)
    private val statusView = itemView.findViewById<TextView>(R.id.status)


    fun onBind(record: Record) {
        record.apply {
            idView.text = siteID
            nameView.text = siteName
            countryView.text = county
            pm25View.text = pm25
            statusView.text = status
        }
    }
}