package com.project.seungjun.model.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.project.seungjun.model.db.entity.TestEntity
import com.project.seungjun.room.BaseDao
import io.reactivex.Maybe

@Dao
interface TestDao: BaseDao<TestEntity> {

    @Query("SELECT * FROM testTB")
    fun selectAll(): Maybe<TestEntity>

    @Query("SELECT * FROM testTB WHERE id = :id")
    fun selectById(id: Int): Maybe<TestEntity>

}