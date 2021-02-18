package com.intive.patronage.verification.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.intive.patronage.verification.db.dao.JokeDao
import com.intive.patronage.verification.db.model.JokeEntry

@Database(entities = [JokeEntry::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "joke.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}