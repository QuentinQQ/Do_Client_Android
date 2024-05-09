package com.example.doapp.db.exercise.official_course

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.doapp.db.personal_info.PersonalInfo
import kotlinx.coroutines.flow.Flow


@Dao
interface OfficialCourseDao {
    @Insert
    suspend fun insertOfficialCourse(course: OfficialCourse)

    @Update
    suspend fun updateOfficialCourse(course: OfficialCourse)

    @Delete
    suspend fun deleteOfficialCourse(course: OfficialCourse)

    @Query("SELECT * FROM OfficialCourse WHERE courseId = :courseId")
    fun getOfficialCourseById(courseId: String): Flow<OfficialCourse>

    @Query("SELECT * FROM OfficialCourse WHERE courseName = :courseName")
    fun getOfficialCourseByName(courseName: String): Flow<OfficialCourse>

    @Query("SELECT * FROM OfficialCourse")
    fun getAllOfficialCourses(): Flow<List<OfficialCourse>>
}
