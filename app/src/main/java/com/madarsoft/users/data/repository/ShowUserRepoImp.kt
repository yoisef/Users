package com.madarsoft.users.data.repository

import com.madarsoft.users.data.db.UsersDatabase
import com.madarsoft.users.domain.models.User
import com.madarsoft.users.domain.repository.ShowUserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class ShowUserRepoImp @Inject constructor(private val usersDatabase: UsersDatabase) : ShowUserRepo {


    override fun getUsers(): Flow<List<User>> =

        flow {

                emit(usersDatabase.getDao().getUsers())

            }


}