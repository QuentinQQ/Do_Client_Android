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


    suspend fun insertUser(userInfo: UserInfo) {
        userInfoDao.insertUser(userInfo)
    }

    suspend fun deleteUser(userInfo: UserInfo) {
        userInfoDao.deleteUser(userInfo)
    }

    suspend fun updateUser(userInfo: UserInfo) {
        userInfoDao.updateUser(userInfo)
    }

    // Personal Info-related methods
    suspend fun insertPersonalInfo(personalInfo: PersonalInfo) {
        personalInfoDao.insertPersonalInfo(personalInfo)
    }

    suspend fun deletePersonalInfo(personalInfo: PersonalInfo) {
        personalInfoDao.deletePersonalInfo(personalInfo)
    }

    suspend fun updatePersonalInfo(personalInfo: PersonalInfo) {
        personalInfoDao.updatePersonalInfo(personalInfo)
    }

    // Preferences-related methods
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
