//package com.example.doapp.db.userinfo
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity
//data class UserInfo(
//    @PrimaryKey(autoGenerate = true)
//    val uid: Int,
//    @ColumnInfo
//    val name: String,
//    @ColumnInfo
//    val email: String
//)
package com.example.doapp.db.userinfo

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.doapp.db.users.Users
import com.google.firebase.firestore.auth.User

@Entity(
    tableName = "UserInfo",
//    foreignKeys = [
//        ForeignKey(
//            entity = Users::class,
//            parentColumns = ["uid"],
//            childColumns = ["uid"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
)
data class UserInfo(
    @PrimaryKey
    val uid: String,
    @ColumnInfo
    val userId: String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    val userName: String,
    @ColumnInfo
    val profilePhotoUrl: String
)
