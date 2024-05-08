package com.example.doapp.db.preferences

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
data class Preferences(
    @PrimaryKey
    val uid: Int
)