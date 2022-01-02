package com.mch.ubiquiti.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository(
    private val webService: WebService,
) {

    var cache = emptyList<Record>()

    suspend fun fetch() = withContext(Dispatchers.IO) {
        webService.fetch().records.also { cache = it }
    }
}