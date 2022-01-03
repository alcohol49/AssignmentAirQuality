package com.mch.ubiquiti.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Record::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recordDao(): RecordDao


    companion object {

        @Volatile
        private var instance: AppDatabase? = null


        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        }

    }
}