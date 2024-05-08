package com.example.doapp.db.users

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.doapp.db.userinfo.UserInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDAO {
    @Query("SELECT * FROM Users ORDER BY uid ASC")
    fun getAllUsersByUid(): Flow<List<Users>>

    @Query("SELECT * FROM Users ORDER BY email ASC")
    fun getAllUsersByEmail(): Flow<List<Users>>

    @Insert
    suspend fun insertUser(userInfo: Users)

    @Update
    suspend fun updateUser(userInfo: Users)

    @Delete
    suspend fun deleteUser(userInfo: Users)
}
