package com.example.doapp.db.personal_info

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalInfoDAO {
    @Query("SELECT * FROM PersonalInfo")
    fun getAllPersonalInfos(): Flow<List<PersonalInfo>>

    @Insert
    suspend fun insertPersonalInfo(personalInfo: PersonalInfo)

    @Update
    suspend fun updatePersonalInfo(personalInfo: PersonalInfo)

    @Delete
    suspend fun deletePersonalInfo(personalInfo: PersonalInfo)
}
