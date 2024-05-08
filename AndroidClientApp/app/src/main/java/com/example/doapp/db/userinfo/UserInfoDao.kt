package com.example.doapp.db.userinfo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//@Dao
//interface UserInfoDAO {
//    @Query("SELECT * FROM UserInfo")
//    fun getAllUserInfoByUid(): Flow<List<UserInfo>>
//
//    @Query("SELECT * FROM UserInfo")
//    fun getAllUserInfoByUserName(): Flow<List<UserInfo>>
//
//    @Query("SELECT * FROM UserInfo")
//    fun getAllUserInfoByEmail(): Flow<List<UserInfo>>
//
//    @Insert
//    suspend fun insertUserInfo(userInfo: UserInfo)
//
//    @Update
//    suspend fun updateUserInfo(userInfo: UserInfo)
//
//    @Delete
//    suspend fun deleteUserInfo(userInfo: UserInfo)
//}

@Dao
interface UserInfoDAO {
    @Query("SELECT * FROM UserInfo ORDER BY uid ASC")
    fun getAllUserInfoByUid(): Flow<List<UserInfo>>

    @Query("SELECT * FROM UserInfo ORDER BY userName ASC")
    fun getAllUserInfoByUserName(): Flow<List<UserInfo>>

    @Query("SELECT * FROM UserInfo WHERE userId = :userId")
    fun getUserInfoById(userId: String?): Flow<UserInfo>

    @Insert
    suspend fun insertUserInfo(userInfo: UserInfo)

    @Update
    suspend fun updateUserInfo(userInfo: UserInfo)

    @Delete
    suspend fun deleteUserInfo(userInfo: UserInfo)
}

