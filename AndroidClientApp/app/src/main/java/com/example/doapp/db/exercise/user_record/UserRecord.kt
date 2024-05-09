package com.example.doapp.db.exercise.user_record

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.doapp.db.exercise.official_course.OfficialCourseSchedule
import com.example.doapp.db.users.Users

@Entity(
    indices = [
        Index(value = ["recordID"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(entity = OfficialCourseSchedule::class,
            parentColumns = ["scheduleId"],
            childColumns = ["detailID"],
            onDelete = ForeignKey.CASCADE)
    ,
        ForeignKey(
            entity = Users::class,
            parentColumns = ["uid"],
            childColumns = ["uid"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserRecord(
    @PrimaryKey
    val recordID: String,
    @ColumnInfo
    val detailID: String,
    @ColumnInfo
    val isCompleted: Boolean,
    @ColumnInfo
    val date: String,
    @ColumnInfo
    val uid: String

)