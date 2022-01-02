package com.mch.ubiquiti.ui

import android.app.Application
import androidx.lifecycle.*
import com.mch.ubiquiti.UbiquitiApp
import com.mch.ubiquiti.data.Record
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy { getApplication<UbiquitiApp>().getRepository() }

    val topRecordsLiveData = MutableLiveData<List<Record>>()
    val bottomRecordsLiveData = MutableLiveData<List<Record>>()

    init {
        viewModelScope.launch {
            val raw = repository.fetch()
            topRecordsLiveData.postValue(
                raw.filter { it.pm25.toInt() <= THRESHOLD }
            )
            bottomRecordsLiveData.postValue(
                raw.filter { it.pm25.toInt() > THRESHOLD }
            )
        }
    }


    companion object {
        private const val TAG = "MainViewModel"

        private const val THRESHOLD = 20
    }
}