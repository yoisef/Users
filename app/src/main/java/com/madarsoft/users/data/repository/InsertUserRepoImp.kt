package com.madarsoft.users.data.repository

import com.madarsoft.users.data.db.UsersDatabase
import com.madarsoft.users.domain.models.User
import com.madarsoft.users.domain.repository.InsertUserRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertUserRepoImp @Inject constructor(private val usersDatabase: UsersDatabase) : InsertUserRepo {


    override fun insertUser(user: User) =
    flow {
        emit(usersDatabase.getDao().insertUser(user))
            }


    }


