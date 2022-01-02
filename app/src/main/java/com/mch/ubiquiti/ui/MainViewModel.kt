package com.mch.ubiquiti.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.mch.ubiquiti.UbiquitiApp

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy { getApplication<UbiquitiApp>().getRepository() }

    var recordsLiveData = liveData {
        val list = repository.fetch()
        emit(list)
    }


    companion object {
        private const val TAG = "MainViewModel"
    }
}