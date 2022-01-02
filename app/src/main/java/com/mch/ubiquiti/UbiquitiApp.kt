package com.mch.ubiquiti

import android.app.Application
import com.mch.ubiquiti.data.DataRepository
import com.mch.ubiquiti.data.WebService

class UbiquitiApp: Application() {

    private fun getWebService() = WebService.create()

    fun getRepository() = DataRepository(getWebService())
}