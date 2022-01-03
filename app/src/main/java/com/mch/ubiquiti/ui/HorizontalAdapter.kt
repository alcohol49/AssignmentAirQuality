package com.mch.ubiquiti.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HorizontalAdapter(
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        return RecyclerViewViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.onBind(viewModel.topRecordsLiveData.value)
    }

    override fun getItemCount(): Int = 1

}
