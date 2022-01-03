package com.mch.ubiquiti.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordDao {

    @Query("SELECT * FROM record")
    fun getRecords(): LiveData<List<Record>>

    @Query("SELECT * FROM record WHERE siteName LIKE '%' || :key || '%' OR county LIKE '%' || :key || '%'")
    fun getRecords(key: String): LiveData<List<Record>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Record>)
}