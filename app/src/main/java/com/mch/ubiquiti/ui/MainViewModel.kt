package com.mch.ubiquiti.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.mch.ubiquiti.UbiquitiApp
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy { getApplication<UbiquitiApp>().getRepository() }

    val topRecordsLiveData = repository.getRecords().switchMap { list ->
        liveData {
            val value = list.filter { it.pm25.isNotEmpty() && it.pm25.toInt() <= THRESHOLD }
            emit(value)
        }
    }
    val bottomRecordsLiveData = repository.getRecords().switchMap { list ->
        liveData {
            val value = list.filter { it.pm25.isNotEmpty() && it.pm25.toInt() > THRESHOLD }
            emit(value)
        }
    }

    init {
        viewModelScope.launch {
            repository.fetch()
        }
    }


    companion object {
        private const val TAG = "MainViewModel"

        private const val THRESHOLD = 20
    }
}