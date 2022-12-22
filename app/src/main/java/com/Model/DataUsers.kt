package com.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class DataUsers: RoomDatabase() {

    abstract fun datalogin(): DataLoginInterface

    companion object{

        @Volatile
        private var INSTANCE : DataUsers? = null


        fun getDatabase(context: Context): DataUsers{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataUsers::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
        }
    }
}}