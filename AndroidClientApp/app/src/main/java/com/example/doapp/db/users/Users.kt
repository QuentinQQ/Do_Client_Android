package com.example.doapp.db.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @PrimaryKey(autoGenerate = false)
    val uid: String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    val password: String
)
