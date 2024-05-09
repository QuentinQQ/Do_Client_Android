package com.example.doapp.db.exercise.official_course

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.doapp.db.exercise.official_course.OfficialCourse
import com.example.doapp.db.userinfo.UserInfo

// 官方计划的每天的计划(但是不包含动作)
@Entity(
    // 设置外键
    foreignKeys = [
        ForeignKey(entity = OfficialCourse::class,
            parentColumns = arrayOf("courseId"),
            childColumns = arrayOf("courseId"),
            onDelete = ForeignKey.CASCADE)
    ],
    // 确保courseId和dayNum的组合唯一
    indices = [
        Index(value = ["courseId", "dayNum"], unique = true)
    ]
)
data class OfficialCourseSchedule(
    @PrimaryKey
    val scheduleId: String,
    @ColumnInfo
    val courseId: String,
    @ColumnInfo
    val scheduleDesc: String,
    @ColumnInfo
    val dayNum: Int,
)