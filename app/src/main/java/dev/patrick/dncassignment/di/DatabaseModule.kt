package dev.patrick.dncassignment.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.patrick.dncassignment.data.local.db.AppDb
import dev.patrick.dncassignment.data.local.db.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDb = AppDb.getInstance(context)

    @Provides
    fun provideUserDao(db: AppDb): UserDao = db.userDao()
}