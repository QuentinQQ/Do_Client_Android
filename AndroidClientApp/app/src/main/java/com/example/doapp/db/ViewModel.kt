package com.example.doapp.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.doapp.db.Repository
import com.example.doapp.db.exercise.fitness_movement.FitnessMovement
import com.example.doapp.db.exercise.official_course.EachActionDetail
import com.example.doapp.db.exercise.official_course.OfficialCourse
import com.example.doapp.db.exercise.official_course.OfficialCourseSchedule
import com.example.doapp.db.exercise.user_record.UserRecord
import com.example.doapp.db.personal_info.PersonalInfo
import com.example.doapp.db.preferences.Preferences
import com.example.doapp.db.userinfo.UserInfo
import com.example.doapp.db.users.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: Repository
    init{
        cRepository = Repository(application)
    }

    /*-----Users-----*/
    val allUsers: LiveData<List<Users>> = cRepository.allUsers.asLiveData()
    fun insertUsers(users: Users) = viewModelScope.launch(Dispatchers.IO) { cRepository.insertUsers(users)
    }
    fun updateSubject(users: Users) = viewModelScope.launch(Dispatchers.IO) { cRepository.updateUsers(users)
    }
    fun deleteSubject(users: Users) = viewModelScope.launch(Dispatchers.IO) { cRepository.deleteUsers(users)
    }

    /*-----User Info-----*/
    val allUserInfo: LiveData<List<UserInfo>> = cRepository.allUserInfo.asLiveData()
    fun getUserInfoById(userId: String): LiveData<UserInfo?> {
        return cRepository.getUserInfoById(userId).asLiveData()
    }
    fun insertUserInfo(userInfo: UserInfo) = viewModelScope.launch (Dispatchers.IO){cRepository.insertUserInfo(userInfo)}
    fun updateUserInfo(userInfo: UserInfo) = viewModelScope.launch (Dispatchers.IO){
        cRepository.getUserInfoById(userInfo.userId).collect { existingUser ->
            if (existingUser != null) {
                cRepository.updateUserInfo(userInfo)
            } else {
                cRepository.insertUserInfo(userInfo)
            }
        }
    }
    fun deleteUserInfo(userInfo: UserInfo) = viewModelScope.launch (Dispatchers.IO){cRepository.deleteUserInfo(userInfo)}


    /*-----Personal Info-----*/
    val allPersonalInfos: LiveData<List<PersonalInfo>> = cRepository.allPersonalInfos.asLiveData()
    fun insertPersonalInfo(personalInfo: PersonalInfo) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insertPersonalInfo(personalInfo)
    }
    fun updatePersonalInfo(personalInfo: PersonalInfo) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.updatePersonalInfo(personalInfo)
    }
    fun deletePersonalInfo(personalInfo: PersonalInfo) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.deletePersonalInfo(personalInfo)
    }

    /*-----Preferences-----*/
    val allPreferences: LiveData<List<Preferences>> = cRepository.allPreferences.asLiveData()
    fun insertPreferences(preferences: Preferences) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insertPreferences(preferences)
    }
    fun updatePreferences(preferences: Preferences) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.updatePreferences(preferences)
    }
    fun deletePreferences(preferences: Preferences) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.deletePreferences(preferences)
    }

    /*-----Fitness Movements-----*/
    fun insertFitnessMovement(movement: FitnessMovement) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insertFitnessMovement(movement)
    }
    fun updateFitnessMovement(movement: FitnessMovement) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.updateFitnessMovement(movement)
    }
    fun deleteFitnessMovement(movement: FitnessMovement) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.deleteFitnessMovement(movement)
    }

    suspend fun getAllFitnessMovements(): LiveData<List<FitnessMovement>> =
        cRepository.getAllFitnessMovements().asLiveData()
    suspend fun getFitnessMovementById(movementId: String): LiveData<FitnessMovement?> =
        cRepository.getFitnessMovementById(movementId).asLiveData()
    suspend fun getAllFitnessMovementsByArea(mainTrainingArea: String): LiveData<List<FitnessMovement>> =
        cRepository.getAllFitnessMovementsByArea(mainTrainingArea).asLiveData()

    /*-----Official Courses-----*/
    fun insertOfficialCourse(course: OfficialCourse) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insertOfficialCourse(course)
    }
    fun updateOfficialCourse(course: OfficialCourse) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.updateOfficialCourse(course)
    }
    fun deleteOfficialCourse(course: OfficialCourse) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.deleteOfficialCourse(course)
    }

    suspend fun getAllOfficialCourses(): LiveData<List<OfficialCourse>> =
        cRepository.getAllOfficialCourses().asLiveData()
    suspend fun getOfficialCourseByName(courseName: String): LiveData<OfficialCourse> =
        cRepository.getOfficialCourseByName(courseName).asLiveData()
    suspend fun getOfficialCourseById(courseId: String): LiveData<OfficialCourse?> =
        cRepository.getOfficialCourseById(courseId).asLiveData()

    /*-----Official Course Schedules-----*/
    fun insertOfficialCourseSchedule(schedule: OfficialCourseSchedule) =
        viewModelScope.launch(Dispatchers.IO) {
            cRepository.insertOfficialCourseSchedule(schedule)
        }
    fun updateOfficialCourseSchedule(schedule: OfficialCourseSchedule) =
        viewModelScope.launch(Dispatchers.IO) {
            cRepository.updateOfficialCourseSchedule(schedule)
        }
    fun deleteOfficialCourseSchedule(schedule: OfficialCourseSchedule) =
        viewModelScope.launch(Dispatchers.IO) {
            cRepository.deleteOfficialCourseSchedule(schedule)
        }
    suspend fun getOfficialCourseScheduleByScheduleId(scheduleId: String): LiveData<OfficialCourseSchedule?> =
        cRepository.getOfficialCourseScheduleByScheduleId(scheduleId).asLiveData()
    suspend fun getOfficialCourseScheduleByCourseId(courseId: String): LiveData<OfficialCourseSchedule> =
        cRepository.getOfficialCourseScheduleByCourseId(courseId).asLiveData()
    suspend fun getAllOfficialCourseSchedules(): LiveData<List<OfficialCourseSchedule>> =
        cRepository.getAllOfficialCourseSchedules().asLiveData()

    /*-----Each Action Details-----*/
    fun insertEachActionDetail(detail: EachActionDetail) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insertEachActionDetail(detail)
    }

    fun updateEachActionDetail(detail: EachActionDetail) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.updateEachActionDetail(detail)
    }

    fun deleteEachActionDetail(detail: EachActionDetail) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.deleteEachActionDetail(detail)
    }

    suspend fun getActionDetailsByScheduleId(scheduleId: String): LiveData<List<EachActionDetail>> =
        cRepository.getActionDetailsByScheduleId(scheduleId).asLiveData()

    suspend fun getActionDetailsByDetailId(detailId: String): LiveData<EachActionDetail?> =
        cRepository.getActionDetailsByDetailId(detailId).asLiveData()

    suspend fun getActionDetailsByMovementId(movementId: String): LiveData<List<EachActionDetail>> =
        cRepository.getActionDetailsByMovementId(movementId).asLiveData()

    suspend fun getActionDetailsBySequenceNum(sequenceNum: Int): LiveData<List<EachActionDetail>> =
        cRepository.getActionDetailsBySequenceNum(sequenceNum).asLiveData()

    suspend fun getActionDetailsByScheduleIdAndSequenceNum(scheduleId: String, sequenceNum: Int): LiveData<EachActionDetail?> =
        cRepository.getActionDetailsByScheduleIdAndSequenceNum(scheduleId, sequenceNum).asLiveData()

    /*-----User Record-----*/
    suspend fun insertUserRecord(record: UserRecord) {
        cRepository.insertUserRecord(record)
    }

    suspend fun updateUserRecord(record: UserRecord) {
        cRepository.updateUserRecord(record)
    }

    suspend fun deleteUserRecord(record: UserRecord) {
        cRepository.deleteUserRecord(record)
    }

    suspend fun getUserRecordById(recordID: String): LiveData<UserRecord> =
        cRepository.getUserRecordById(recordID).asLiveData()

    suspend fun getAllUserRecords(): LiveData<List<UserRecord>> =
        cRepository.getAllUserRecords().asLiveData()
}

