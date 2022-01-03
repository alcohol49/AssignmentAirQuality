package com.mch.ubiquiti.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.mch.ubiquiti.R

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by viewModels<MainViewModel>()

    private val firstAdapter by lazy { HorizontalAdapter(viewModel) }
    private val secondAdapter by lazy { RecordListAdapter() }
    private val adapter by lazy { ConcatAdapter(firstAdapter, secondAdapter) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            findViewById<MaterialToolbar>(R.id.toolbar).setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.search -> {
                        findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
                        true
                    }
                    else -> false
                }
            }
            findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
        }

        viewModel.apply {
            topRecordsLiveData.observe(viewLifecycleOwner) {
                firstAdapter.notifyItemChanged(0)
            }
            bottomRecordsLiveData.observe(viewLifecycleOwner) {
                secondAdapter.submitList(it)
            }
        }
    }


    companion object {
        private const val TAG = "MainFragment"
    }
}