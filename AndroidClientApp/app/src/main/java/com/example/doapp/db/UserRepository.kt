package com.example.doapp.db

import android.app.Application
import kotlinx.coroutines.flow.Flow

class UserRepository(application: Application) {
    private var userInfoDao: UserInfoDAO = AppDatabase.getDatabase(application).userInfoDAO()
    private var personalInfoDao: PersonalInfoDAO =
        AppDatabase.getDatabase(application).personalInfoDAO()
    private var preferencesDao: PreferencesDAO =
        AppDatabase.getDatabase(application).preferencesDAO()

    val allUsers: Flow<List<UserInfo>> = userInfoDao.getAllUsers()
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
