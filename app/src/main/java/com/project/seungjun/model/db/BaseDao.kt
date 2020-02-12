package com.project.seungjun.model.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArray(vararg datas: T)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(data: T)

    @Delete
    fun delete(data: T)

}