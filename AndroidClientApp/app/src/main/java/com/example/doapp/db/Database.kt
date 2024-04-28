package com.example.doapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.doapp.db.PersonalInfo
import com.example.doapp.db.PersonalInfoDAO
import com.example.doapp.db.Preferences
import com.example.doapp.db.PreferencesDAO
import com.example.doapp.db.UserInfo
import com.example.doapp.db.UserInfoDAO

@Database(entities = [UserInfo::class, PersonalInfo::class, Preferences::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDAO(): UserInfoDAO
    abstract fun personalInfoDAO(): PersonalInfoDAO
    abstract fun preferencesDAO(): PreferencesDAO

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
