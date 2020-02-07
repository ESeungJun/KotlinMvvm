package com.project.utils.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class RoomHelper : RoomDatabase() {


    companion object {

        private val DB_NAME = "testDB"


        private var instance: RoomHelper? = null

        fun getInstance(context: Context) = instance
            ?: Room.databaseBuilder(context, RoomHelper::class.java, DB_NAME)
                .build().also { instance = it }

    }
}