package com.madarsoft.users.di

import android.content.Context
import androidx.room.Room
import com.madarsoft.users.data.db.UsersDao
import com.madarsoft.users.data.db.UsersDatabase
import com.madarsoft.users.utils.Database_Name

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): UsersDatabase {
            return Room.databaseBuilder(
                appContext,
                UsersDatabase::class.java,
                Database_Name
            ).build()
        }

    @Provides
    fun provideLogDao(database: UsersDatabase): UsersDao {
            return database.getDao()
        }


}