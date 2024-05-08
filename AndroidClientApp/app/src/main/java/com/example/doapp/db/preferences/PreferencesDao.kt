package com.example.doapp.db.preferences

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

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
