package com.example.doapp.db

import android.app.Application
import com.example.doapp.db.personalInfo.PersonalInfo
import com.example.doapp.db.personalInfo.PersonalInfoDAO
import com.example.doapp.db.preferences.Preferences
import com.example.doapp.db.preferences.PreferencesDAO
import com.example.doapp.db.userinfo.UserInfo
import com.example.doapp.db.userinfo.UserInfoDAO
import com.example.doapp.db.users.Users
import com.example.doapp.db.users.UsersDAO
import kotlinx.coroutines.flow.Flow

class Repository(application: Application) {
    private var usersDao: UsersDAO = AppDatabase.getDatabase(application).usersDao()
    private var userInfoDao: UserInfoDAO = AppDatabase.getDatabase(application).userInfoDAO()
    private var personalInfoDao: PersonalInfoDAO = AppDatabase.getDatabase(application).personalInfoDAO()
    private var preferencesDao: PreferencesDAO = AppDatabase.getDatabase(application).preferencesDAO()

    val allUsers: Flow<List<Users>> = usersDao.getAllUsersByUid()
    val allUserInfo: Flow<List<UserInfo>> = userInfoDao.getAllUserInfoByUid()
    val allPersonalInfos: Flow<List<PersonalInfo>> = personalInfoDao.getAllPersonalInfos()
    val allPreferences: Flow<List<Preferences>> = preferencesDao.getAllPreferences()

    /*-------Users-------*/
    suspend fun insertUsers(users: Users) {
        usersDao.insertUser(users)
    }

    suspend fun deleteUsers(users: Users) {
        usersDao.deleteUser(users)
    }

    suspend fun updateUsers(users: Users) {
        usersDao.updateUser(users)
    }

    /*-------Users Info-------*/
    fun getUserInfoById(userId: String): Flow<UserInfo?> {
        return userInfoDao.getUserInfoById(userId)
    }

    suspend fun insertUserInfo(userInfo: UserInfo) {
        userInfoDao.insertUserInfo(userInfo)
    }

    suspend fun deleteUserInfo(userInfo: UserInfo) {
        userInfoDao.deleteUserInfo(userInfo)
    }

    suspend fun updateUserInfo(userInfo: UserInfo) {
        userInfoDao.updateUserInfo(userInfo)
    }

    /*-------Personal Info-------*/
    suspend fun insertPersonalInfo(personalInfo: PersonalInfo) {
        personalInfoDao.insertPersonalInfo(personalInfo)
    }

    suspend fun deletePersonalInfo(personalInfo: PersonalInfo) {
        personalInfoDao.deletePersonalInfo(personalInfo)
    }

    suspend fun updatePersonalInfo(personalInfo: PersonalInfo) {
        personalInfoDao.updatePersonalInfo(personalInfo)
    }

    /*-------Preferences-------*/
    suspend fun insertPreferences(preferences: Preferences) {
        preferencesDao.insertPreferences(preferences)
    }

    suspend fun deletePreferences(preferences: Preferences) {
        preferencesDao.deletePreferences(preferences)
    }

    suspend fun updatePreferences(preferences: Preferences) {
        preferencesDao.updatePreferences(preferences)
    }
}
