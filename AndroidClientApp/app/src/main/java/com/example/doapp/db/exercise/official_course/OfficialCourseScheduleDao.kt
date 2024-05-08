package com.example.doapp.db.exercise.official_course

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OfficialCourseScheduleDao {
    @Insert
    suspend fun insertOfficialCourseSchedule(schedule: OfficialCourseSchedule)

    @Update
    suspend fun updateOfficialCourseSchedule(schedule: OfficialCourseSchedule)

    @Delete
    suspend fun deleteOfficialCourseSchedule(schedule: OfficialCourseSchedule)

    @Query("SELECT * FROM OfficialCourseSchedule WHERE scheduleId = :scheduleId")
    fun getOfficialCourseScheduleByScheduleId(scheduleId: String): Flow<OfficialCourseSchedule>

    @Query("SELECT * FROM OfficialCourseSchedule WHERE courseId = :courseId")
    fun getOfficialCourseScheduleByCourseId(courseId: String): Flow<OfficialCourseSchedule>

    @Query("SELECT * FROM OfficialCourseSchedule")
    fun getAllOfficialCourseSchedules(): Flow<List<OfficialCourseSchedule>>
}