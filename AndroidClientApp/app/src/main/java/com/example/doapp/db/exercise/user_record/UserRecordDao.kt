package com.example.doapp.db.exercise.user_record

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserRecordDao {
    @Insert
    suspend fun insertUserRecord(record: UserRecord)

    @Update
    suspend fun updateUserRecord(record: UserRecord)

    @Delete
    suspend fun deleteUserRecord(record: UserRecord)

    @Query("SELECT * FROM UserRecord WHERE recordID = :recordID")
    fun getUserRecordById(recordID: String): Flow<UserRecord>

    @Query("SELECT * FROM UserRecord")
    fun getAllUserRecords(): Flow<List<UserRecord>>
}