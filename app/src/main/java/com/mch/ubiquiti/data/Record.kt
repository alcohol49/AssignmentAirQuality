package com.mch.ubiquiti.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Record(
    @PrimaryKey
    @field:SerializedName("SiteId") val siteID: String,
    @field:SerializedName("SiteName") val siteName: String,
    @field:SerializedName("County") val county: String,
    @field:SerializedName("PM2.5") val pm25: String,
    @field:SerializedName("Status") val status: String,
)

