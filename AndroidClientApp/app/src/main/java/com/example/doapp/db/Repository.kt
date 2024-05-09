package com.example.doapp.db

import android.app.Application
import com.example.doapp.db.exercise.fitness_movement.FitnessMovement
import com.example.doapp.db.exercise.fitness_movement.FitnessMovementDao
import com.example.doapp.db.exercise.official_course.EachActionDetail
import com.example.doapp.db.exercise.official_course.EachActionDetailDao
import com.example.doapp.db.exercise.official_course.OfficialCourse
import com.example.doapp.db.exercise.official_course.OfficialCourseDao
import com.example.doapp.db.exercise.official_course.OfficialCourseSchedule
import com.example.doapp.db.exercise.official_course.OfficialCourseScheduleDao
import com.example.doapp.db.exercise.user_record.UserRecord
import com.example.doapp.db.exercise.user_record.UserRecordDao
import com.example.doapp.db.personal_info.PersonalInfo
import com.example.doapp.db.personal_info.PersonalInfoDAO
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
    private var fitnessMovementDao: FitnessMovementDao = AppDatabase.getDatabase(application).fitnessMovementDao()
    private var officialCourseDao: OfficialCourseDao = AppDatabase.getDatabase(application).officialCourseDao()
    private var officialCourseScheduleDao: OfficialCourseScheduleDao = AppDatabase.getDatabase(application).officialCourseScheduleDao()
    private var eachActionDetailDao: EachActionDetailDao = AppDatabase.getDatabase(application).eachActionDetailDao()
    private val userRecordDao: UserRecordDao = AppDatabase.getDatabase(application).userRecordDao()

    val allUsers: Flow<List<Users>> = usersDao.getAllUsersByUid()
    val allUserInfo: Flow<List<UserInfo>> = userInfoDao.getAllUserInfoByUid()
    val allPersonalInfos: Flow<List<PersonalInfo>> = personalInfoDao.getAllPersonalInfos()
    val allPreferences: Flow<List<Preferences>> = preferencesDao.getAllPreferences()

    // Users
    suspend fun insertUsers(users: Users) {
        usersDao.insertUser(users)
    }

    suspend fun deleteUsers(users: Users) {
        usersDao.deleteUser(users)
    }

    suspend fun updateUsers(users: Users) {
        usersDao.updateUser(users)
    }

    // Users Info
    fun getUserInfoById(userId: String): Flow<UserInfo> {
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

    // Personal Info
    suspend fun insertPersonalInfo(personalInfo: PersonalInfo) {
        personalInfoDao.insertPersonalInfo(personalInfo)
    }

    suspend fun deletePersonalInfo(personalInfo: PersonalInfo) {
        personalInfoDao.deletePersonalInfo(personalInfo)
    }

    suspend fun updatePersonalInfo(personalInfo: PersonalInfo) {
        personalInfoDao.updatePersonalInfo(personalInfo)
    }

    // Preferences
    suspend fun insertPreferences(preferences: Preferences) {
        preferencesDao.insertPreferences(preferences)
    }

    suspend fun deletePreferences(preferences: Preferences) {
        preferencesDao.deletePreferences(preferences)
    }

    suspend fun updatePreferences(preferences: Preferences) {
        preferencesDao.updatePreferences(preferences)
    }

    // Fitness Movements
    suspend fun insertFitnessMovement(movement: FitnessMovement) {
        fitnessMovementDao.insertFitnessMovement(movement)
    }

    suspend fun updateFitnessMovement(movement: FitnessMovement) {
        fitnessMovementDao.updateFitnessMovement(movement)
    }

    suspend fun deleteFitnessMovement(movement: FitnessMovement) {
        fitnessMovementDao.deleteFitnessMovement(movement)
    }
    suspend fun getAllFitnessMovements(): Flow<List<FitnessMovement>> =
        fitnessMovementDao.getAllFitnessMovements()
    suspend fun getFitnessMovementById(movementId: String): Flow<FitnessMovement?> =
        fitnessMovementDao.getFitnessMovementById(movementId)
    suspend fun getAllFitnessMovementsByArea(mainTrainingArea: String): Flow<List<FitnessMovement>> =
        fitnessMovementDao.getAllFitnessMovementsByArea(mainTrainingArea)


    // Official Courses
    suspend fun insertOfficialCourse(course: OfficialCourse) {
        officialCourseDao.insertOfficialCourse(course)
    }

    suspend fun updateOfficialCourse(course: OfficialCourse) {
        officialCourseDao.updateOfficialCourse(course)
    }

    suspend fun deleteOfficialCourse(course: OfficialCourse) {
        officialCourseDao.deleteOfficialCourse(course)
    }

    suspend fun getAllOfficialCourses(): Flow<List<OfficialCourse>> =
        officialCourseDao.getAllOfficialCourses()
    suspend fun getOfficialCourseByName(courseName: String): Flow<OfficialCourse> =
        officialCourseDao.getOfficialCourseByName(courseName)
    suspend fun getOfficialCourseById(courseId: String): Flow<OfficialCourse> =
        officialCourseDao.getOfficialCourseById(courseId)


    // Official Course Schedules
    suspend fun insertOfficialCourseSchedule(schedule: OfficialCourseSchedule) {
        officialCourseScheduleDao.insertOfficialCourseSchedule(schedule)
    }

    suspend fun updateOfficialCourseSchedule(schedule: OfficialCourseSchedule) {
        officialCourseScheduleDao.updateOfficialCourseSchedule(schedule)
    }

    suspend fun deleteOfficialCourseSchedule(schedule: OfficialCourseSchedule) {
        officialCourseScheduleDao.deleteOfficialCourseSchedule(schedule)
    }

    suspend fun getOfficialCourseScheduleByScheduleId(scheduleId: String): Flow<OfficialCourseSchedule> =
        officialCourseScheduleDao.getOfficialCourseScheduleByScheduleId(scheduleId)

    suspend fun getOfficialCourseScheduleByCourseId(courseId: String): Flow<OfficialCourseSchedule> =
        officialCourseScheduleDao.getOfficialCourseScheduleByCourseId(courseId)

    suspend fun getAllOfficialCourseSchedules(): Flow<List<OfficialCourseSchedule>> =
        officialCourseScheduleDao.getAllOfficialCourseSchedules()

    // Each Action Details
    suspend fun insertEachActionDetail(detail: EachActionDetail) {
        eachActionDetailDao.insertEachActionDetail(detail)
    }

    suspend fun updateEachActionDetail(detail: EachActionDetail) {
        eachActionDetailDao.updateEachActionDetail(detail)
    }

    suspend fun deleteEachActionDetail(detail: EachActionDetail) {
        eachActionDetailDao.deleteEachActionDetail(detail)
    }

    suspend fun getActionDetailsByScheduleId(scheduleId: String): Flow<List<EachActionDetail>> =
        eachActionDetailDao.getActionDetailsByScheduleId(scheduleId)

    suspend fun getActionDetailsByDetailId(detailId: String): Flow<EachActionDetail?> =
        eachActionDetailDao.getEachActionDetailByDetailId(detailId)

    suspend fun getActionDetailsByMovementId(movementId: String): Flow<List<EachActionDetail>> =
        eachActionDetailDao.getActionDetailsByMovementId(movementId)

    suspend fun getActionDetailsBySequenceNum(sequenceNum: Int): Flow<List<EachActionDetail>> =
        eachActionDetailDao.getActionDetailsBySequenceNum(sequenceNum)

    suspend fun getActionDetailsByScheduleIdAndSequenceNum(scheduleId: String, sequenceNum: Int): Flow<EachActionDetail?> =
        eachActionDetailDao.getActionDetailsByScheduleIdAndSequenceNum(scheduleId, sequenceNum)

    suspend fun insertUserRecord(record: UserRecord) {
        userRecordDao.insertUserRecord(record)
    }

    suspend fun updateUserRecord(record: UserRecord) {
        userRecordDao.updateUserRecord(record)
    }

    suspend fun deleteUserRecord(record: UserRecord) {
        userRecordDao.deleteUserRecord(record)
    }

    suspend fun getUserRecordById(recordID: String): Flow<UserRecord> =
        userRecordDao.getUserRecordById(recordID)

    suspend fun getAllUserRecords(): Flow<List<UserRecord>> =
        userRecordDao.getAllUserRecords()
}

