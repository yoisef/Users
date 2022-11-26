package com.madarsoft.users.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madarsoft.users.data.db.UsersDao
import com.madarsoft.users.domain.models.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun getDao(): UsersDao






}