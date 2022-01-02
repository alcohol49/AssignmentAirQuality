package com.mch.ubiquiti.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mch.ubiquiti.R

class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel by viewModels<MainViewModel>()

    private val topAdapter by lazy { TopRecordAdapter() }
    private val bottomAdapter by lazy { RecordAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.top_recycler_view).adapter = topAdapter
        view.findViewById<RecyclerView>(R.id.bottom_recycler_view).adapter = bottomAdapter

        viewModel.apply {
            topRecordsLiveData.observe(viewLifecycleOwner) {
                topAdapter.submitList(it)
            }
            bottomRecordsLiveData.observe(viewLifecycleOwner) {
                bottomAdapter.submitList(it)
            }
        }
    }


    companion object {
        private const val TAG = "MainFragment"
    }
}