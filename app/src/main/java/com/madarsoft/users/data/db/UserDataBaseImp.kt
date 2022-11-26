package com.madarsoft.users.data.db

import com.madarsoft.users.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserDataBaseImp(private val usersDatabase: UsersDatabase) : UsersDatabaseRepo {

 override suspend fun insertUser(userO: User): Flow<Long> =
  flow {

   emit(usersDatabase.getDao().insertUser(userO))

  }


 override fun getUsers(): Flow<List<User>> = flow { emit(usersDatabase.getDao().getUsers()) }
 override fun findUser(name: String): Flow<User> =
  flow { emit(usersDatabase.getDao().findUser(name)) }

 override suspend fun deleteUsers() {
  flow {


   emit(usersDatabase.getDao().deleteAllUsers())
  }
 }
}

