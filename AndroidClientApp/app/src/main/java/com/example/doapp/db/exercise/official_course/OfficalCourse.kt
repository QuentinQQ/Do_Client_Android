package com.example.doapp.db.exercise.official_course

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.doapp.db.userinfo.UserInfo

@Entity(
    indices = [
        Index(value = ["courseName"], unique = true)
    ]
)
data class OfficialCourse(
    @PrimaryKey
    val courseId: String,
    @ColumnInfo
    val courseName: String,
    @ColumnInfo
    val courseLength: Int,
    @ColumnInfo
    val courseDesc: String,
)