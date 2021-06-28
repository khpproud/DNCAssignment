package dev.patrick.dncassignment.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.patrick.dncassignment.data.local.LocalDataSource
import dev.patrick.dncassignment.data.local.LocalDataSourceImpl
import dev.patrick.dncassignment.data.remote.RemoteDataSource
import dev.patrick.dncassignment.data.remote.RemoteDataSourceImpl
import dev.patrick.dncassignment.data.repository.UserRepositoryImpl
import dev.patrick.dncassignment.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindRepository(repo: UserRepositoryImpl): UserRepository

    @Binds
    fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource
}