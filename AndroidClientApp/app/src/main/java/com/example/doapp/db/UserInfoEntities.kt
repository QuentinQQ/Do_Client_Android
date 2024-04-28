package com.example.doapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date


@Entity
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val email: String
)


@Entity(foreignKeys = [
    ForeignKey(entity = UserInfo::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("uid"),
        onDelete = ForeignKey.CASCADE)
])
data class PersonalInfo(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo
    val gender: String,
    @ColumnInfo
    val birthMonth: String,  // The storage format is "YYYY-MM"
    @ColumnInfo
    val height: Int,
    @ColumnInfo
    val weight: Float
)


@Entity(foreignKeys = [
    ForeignKey(entity = UserInfo::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("uid"),
        onDelete = ForeignKey.CASCADE)
])
data class Preferences(
    @PrimaryKey
    val uid: Int
)

