package com.project.seungjun.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "testTB")
data class TestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var text: String
)