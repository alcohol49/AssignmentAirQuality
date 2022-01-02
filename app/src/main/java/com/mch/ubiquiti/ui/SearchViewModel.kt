package com.mch.ubiquiti.ui

import android.app.Application
import androidx.lifecycle.*
import com.mch.ubiquiti.UbiquitiApp
import kotlinx.coroutines.Dispatchers

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository by lazy { getApplication<UbiquitiApp>().getRepository() }

    val keyLiveData = MutableLiveData<CharSequence>()

    val list = keyLiveData.switchMap { key ->
        liveData(viewModelScope.coroutineContext + Dispatchers.Default) {
            if (key.isEmpty()) {
                emit(emptyList())
            } else {
                val filtered = repository.fetch().filter {
                    it.siteName.contains(key) || it.county.contains(key)
                }
                emit(filtered)
            }
        }
    }
}