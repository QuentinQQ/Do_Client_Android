package com.example.doapp.db.exercise.official_course

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EachActionDetailDao {
    @Insert
    suspend fun insertEachActionDetail(detail: EachActionDetail)

    @Update
    suspend fun updateEachActionDetail(detail: EachActionDetail)

    @Delete
    suspend fun deleteEachActionDetail(detail: EachActionDetail)

    @Query("SELECT * FROM EachActionDetail WHERE detailId = :detailId")
    fun getEachActionDetailByDetailId(detailId: String): Flow<EachActionDetail?>

    @Query("SELECT * FROM EachActionDetail WHERE scheduleId = :scheduleId")
    fun getActionDetailsByScheduleId(scheduleId: String): Flow<List<EachActionDetail>>

    @Query("SELECT * FROM EachActionDetail WHERE movementId = :movementId")
    fun getActionDetailsByMovementId(movementId: String): Flow<List<EachActionDetail>>

    @Query("SELECT * FROM EachActionDetail WHERE sequenceNum = :sequenceNum")
    fun getActionDetailsBySequenceNum(sequenceNum: Int): Flow<List<EachActionDetail>>

    @Query("SELECT * FROM EachActionDetail WHERE scheduleId = :scheduleId AND sequenceNum = :sequenceNum")
    fun getActionDetailsByScheduleIdAndSequenceNum(scheduleId: String, sequenceNum: Int): Flow<EachActionDetail?>
}