package com.mch.ubiquiti

import android.app.Application
import com.mch.ubiquiti.data.AppDatabase
import com.mch.ubiquiti.data.DataRepository
import com.mch.ubiquiti.data.WebService

class UbiquitiApp: Application() {

    private fun getWebService() = WebService.create()
    private fun getDatabase() = AppDatabase.getInstance(this)

    fun getRepository() = DataRepository(getWebService(), getDatabase().recordDao())
}