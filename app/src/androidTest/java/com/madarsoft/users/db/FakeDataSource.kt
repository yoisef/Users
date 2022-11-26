package com.madarsoft.users.db

import com.madarsoft.users.data.db.UsersDatabaseRepo
import com.madarsoft.users.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDataSource(var users: MutableList<User> = mutableListOf()) : UsersDatabaseRepo {


    override suspend fun insertUser(user: User) : Flow<Long> = flow {

       val result= if (users.add((user))) 1L else -1L
        emit(result )

    }

    // return


    override fun getUsers(): Flow<List<User>> {

        return flow { emit(users) }
    }

    override fun findUser(userName: String): Flow<User> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUsers() {
        TODO("Not yet implemented")
    }


}