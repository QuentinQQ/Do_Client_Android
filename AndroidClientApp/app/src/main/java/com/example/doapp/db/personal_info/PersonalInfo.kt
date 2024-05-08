package com.example.doapp.db.personal_info

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.doapp.db.userinfo.UserInfo

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
