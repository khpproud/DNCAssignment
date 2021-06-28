package dev.patrick.dncassignment.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: createDb(context).also {
                    INSTANCE = it
                }
            }

        private fun createDb(context: Context): AppDb = Room.databaseBuilder(
            context.applicationContext,
            AppDb::class.java,
            "user.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}