package com.example.doapp.db.exercise.fitness_movement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// 每个动作的信息
@Entity
data class FitnessMovement(
    @PrimaryKey
    val movementId: String,
    @ColumnInfo
    val movementName: String,
    @ColumnInfo
    val mainTrainingArea: String,
)
