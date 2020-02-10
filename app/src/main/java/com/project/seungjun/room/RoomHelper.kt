package com.project.seungjun.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.seungjun.model.db.dao.TestDao
import com.project.seungjun.model.db.entity.TestEntity

@Database(entities = [TestEntity::class], version = 1)
abstract class RoomHelper : RoomDatabase() {

    abstract fun TestDao(): TestDao

    companion object {

        private val DB_NAME = "testDB"


        private var instance: RoomHelper? = null

        fun getInstance(context: Context) = instance
            ?: Room.databaseBuilder(context, RoomHelper::class.java,
                DB_NAME
            )
                .build().also { instance = it }

    }
}