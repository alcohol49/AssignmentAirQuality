package com.mch.ubiquiti.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mch.ubiquiti.R
import com.mch.ubiquiti.data.Record

class RecordListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_record_list, parent, false)
) {

    private val idView = itemView.findViewById<TextView>(R.id.site_id)
    private val nameView = itemView.findViewById<TextView>(R.id.site_name)
    private val countryView = itemView.findViewById<TextView>(R.id.country)
    private val pm25View = itemView.findViewById<TextView>(R.id.pm25)
    private val statusView = itemView.findViewById<TextView>(R.id.status)
    private val arrow = itemView.findViewById<View>(R.id.next)


    fun onBind(record: Record) {
        record.apply {
            idView.text = siteID
            nameView.text = siteName
            countryView.text = county
            pm25View.text = pm25

            if (status != "良好") {
                arrow.isVisible = true
                statusView.text = status
                itemView.setOnClickListener {
                    Toast.makeText(itemView.context, "空氣不是太好 \uD83D\uDE2E", Toast.LENGTH_SHORT).show()
                }
            } else {
                arrow.isVisible = false
                statusView.text = "The status is good, we want to go out to have fun"
                itemView.isClickable = false
            }
        }
    }
}