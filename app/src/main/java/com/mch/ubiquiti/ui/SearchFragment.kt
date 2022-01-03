package com.mch.ubiquiti.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.mch.ubiquiti.R

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel by viewModels<SearchViewModel>()

    private val adapter by lazy { RecordListAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            findViewById<View>(R.id.back).setOnClickListener { findNavController().popBackStack() }
            findViewById<TextInputEditText>(R.id.edit_text).doOnTextChanged { text, _, _, _ ->
                findViewById<TextView>(R.id.empty_info).isVisible = text?.length == 0
                viewModel.keyLiveData.postValue(text)
            }
            findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
        }

        viewModel.apply {
            list.observe(viewLifecycleOwner) {
                adapter.submitList(it)

                view.findViewById<TextView>(R.id.search_info).apply {
                    isVisible = it.isNullOrEmpty() && keyLiveData.value?.isNotEmpty() == true
                    text = "找不到「${keyLiveData.value}」相關的空污資訊"
                }
            }
        }
    }


    companion object {
        private const val TAG = "SearchFragment"
    }
}