package com.mch.ubiquiti.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository(
    private val webService: WebService,
    private val recordDao: RecordDao
) {

    fun getRecords() = recordDao.getRecords()

    fun getRecords(key: String) = recordDao.getRecords(key)

    suspend fun fetch() = withContext(Dispatchers.IO) {
        val list = webService.fetch().records
        recordDao.insertAll(list)
    }
}