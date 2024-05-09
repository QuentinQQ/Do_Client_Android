package com.example.doapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.doapp.db.exercise.fitness_movement.FitnessMovement
import com.example.doapp.db.exercise.fitness_movement.FitnessMovementDao
import com.example.doapp.db.exercise.official_course.EachActionDetail
import com.example.doapp.db.exercise.official_course.EachActionDetailDao
import com.example.doapp.db.exercise.official_course.OfficialCourse
import com.example.doapp.db.exercise.official_course.OfficialCourseDao
import com.example.doapp.db.exercise.official_course.OfficialCourseSchedule
import com.example.doapp.db.exercise.official_course.OfficialCourseScheduleDao
import com.example.doapp.db.preferences.Preferences
import com.example.doapp.db.preferences.PreferencesDAO
import com.example.doapp.db.personal_info.PersonalInfo
import com.example.doapp.db.personal_info.PersonalInfoDAO
import com.example.doapp.db.userinfo.UserInfo
import com.example.doapp.db.userinfo.UserInfoDAO
import com.example.doapp.db.users.Users
import com.example.doapp.db.users.UsersDAO

@Database(
    entities = [
        Users::class,
        UserInfo::class,
        PersonalInfo::class,
        Preferences::class,
        FitnessMovement::class,
        OfficialCourse::class,
        OfficialCourseSchedule::class,
        EachActionDetail::class
    ],
    version = 7,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDAO
    abstract fun userInfoDAO(): UserInfoDAO
    abstract fun personalInfoDAO(): PersonalInfoDAO
    abstract fun preferencesDAO(): PreferencesDAO
    abstract fun fitnessMovementDao(): FitnessMovementDao
    abstract fun officialCourseDao(): OfficialCourseDao
    abstract fun officialCourseScheduleDao(): OfficialCourseScheduleDao
    abstract fun eachActionDetailDao(): EachActionDetailDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
