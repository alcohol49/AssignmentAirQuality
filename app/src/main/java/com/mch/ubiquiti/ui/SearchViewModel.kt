package com.mch.ubiquiti.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.mch.ubiquiti.UbiquitiApp
import com.mch.ubiquiti.data.Record
import kotlinx.coroutines.Dispatchers

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy { getApplication<UbiquitiApp>().getRepository() }

    val keyLiveData = MutableLiveData<CharSequence>()

    val list = keyLiveData.switchMap {
        if (it.isEmpty()) {
            // In order to show not found
            liveData { emit(emptyList()) }
        } else {
            repository.getRecords(it.toString())
        }
    }
}