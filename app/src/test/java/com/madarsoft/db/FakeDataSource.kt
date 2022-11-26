package com.madarsoft.db

import com.madarsoft.users.data.db.UsersDatabaseRepo
import com.madarsoft.users.domain.models.User
import com.madarsoft.users.domain.repository.InsertUserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDataSource(var users: MutableList<User> = mutableListOf()) : InsertUserRepo {
    override fun insertUser(user: User): Flow<Long> {
        return  flow {
          val result=  if (users.add(user)) 1L else -1L
            emit(result)
        }


        }
    }



