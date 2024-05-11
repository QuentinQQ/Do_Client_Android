package com.example.doapp.db.exercise.official_course

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.doapp.db.exercise.fitness_movement.FitnessMovement

@Entity(
//    foreignKeys = [
//        ForeignKey(entity = OfficialCourseSchedule::class,
//            parentColumns = arrayOf("scheduleId"),
//            childColumns = arrayOf("scheduleId"),
//            onDelete = ForeignKey.CASCADE),
//        ForeignKey(entity = FitnessMovement::class,
//            parentColumns = arrayOf("movementId"),
//            childColumns = arrayOf("movementId"),
//            onDelete = ForeignKey.CASCADE)
//    ],
    // 确保courseId和dayNum的组合唯一
    indices = [
        Index(value = ["sequenceNum", "scheduleId"], unique = true)
    ]
)
data class EachActionDetail(
    @PrimaryKey
    val detailId: String,
    val scheduleId: String, // 外键，关联到 OfficialCourseSchedule
    val movementId: String, // 动作id
    val movementName: String,
    val sequenceNum: Int, // 动作的顺序编号，在同一 OfficialCourseSchedule 内唯一
    val sets: Int, // 组数
    val weight: Double // 重量
)


