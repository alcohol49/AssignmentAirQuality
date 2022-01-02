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

    private val adapter by lazy { RecordAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        viewModel.recordsLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: $it")
            adapter.submitList(it)
        }
    }


    companion object {
        private const val TAG = "MainFragment"
    }
}