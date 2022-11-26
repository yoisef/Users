package com.madarsoft.users.data.db

import com.madarsoft.users.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UsersDatabaseRepo {

    suspend fun insertUser(users: User) : Flow<Long>

    fun getUsers(): Flow<List<User>>

    fun findUser(userName : String): Flow<User?>

    suspend fun deleteUsers()


}