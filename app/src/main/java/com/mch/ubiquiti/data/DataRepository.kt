package com.mch.ubiquiti.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository private constructor(
    private val webService: WebService,
) {

    private var cache = emptyList<Record>()

    suspend fun fetch() = withContext(Dispatchers.IO) {
        if (cache.isEmpty()) {
            cache = webService.fetch().records
        }
        cache
    }


    companion object {

        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(webService: WebService) = instance ?: synchronized(this) {
            instance ?: DataRepository(webService).also {
                instance = it
            }
        }
    }
}