package com.example.doapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDAO {
    @Query("SELECT * FROM UserInfo")
    fun getAllUsers(): Flow<List<UserInfo>>

    @Insert
    suspend fun insertUser(userInfo: UserInfo)

    @Update
    suspend fun updateUser(userInfo: UserInfo)

    @Delete
    suspend fun deleteUser(userInfo: UserInfo)
}


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



@Dao
interface PreferencesDAO {
    @Query("SELECT * FROM Preferences")
    fun getAllPreferences(): Flow<List<Preferences>>

    @Insert
    suspend fun insertPreferences(preferences: Preferences): Long

    @Update
    suspend fun updatePreferences(preferences: Preferences)

    @Delete
    suspend fun deletePreferences(preferences: Preferences)
}

