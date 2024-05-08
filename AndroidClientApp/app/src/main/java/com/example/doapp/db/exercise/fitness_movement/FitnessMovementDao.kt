package com.example.doapp.db.exercise.fitness_movement

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessMovementDao {
    @Insert
    suspend fun insertFitnessMovement(movement: FitnessMovement)

    @Update
    suspend fun updateFitnessMovement(movement: FitnessMovement)

    @Delete
    suspend fun deleteFitnessMovement(movement: FitnessMovement)

    @Query("SELECT * FROM FitnessMovement WHERE movementId = :movementId")
    fun getFitnessMovementById(movementId: String): Flow<FitnessMovement>

    @Query("SELECT * FROM FitnessMovement")
    fun getAllFitnessMovements(): Flow<List<FitnessMovement>>

    @Query("SELECT * FROM FitnessMovement WHERE mainTrainingArea = :mainTrainingArea")
    fun getAllFitnessMovementsByArea(mainTrainingArea: String): Flow<List<FitnessMovement>>
}