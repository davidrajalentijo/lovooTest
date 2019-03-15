package com.example.david.lovoo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.david.lovoo.converters.LovooFactConverter
import com.example.david.lovoo.models.Room

/*
     Room database creation
 */
@Database(entities = [(Room::class)], version = 1, exportSchema = false)
@TypeConverters(LovooFactConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        private const val DATABASE_NAME: String = "RoomsList"

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = android.arch.persistence.room.Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, DATABASE_NAME)
                            .build()
                }
            }
            return instance as AppDatabase
        }
    }

    abstract fun roomsDao(): RoomsDao

}