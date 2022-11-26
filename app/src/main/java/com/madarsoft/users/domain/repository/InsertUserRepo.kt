package com.madarsoft.users.domain.repository

import com.madarsoft.users.domain.models.User
import kotlinx.coroutines.flow.Flow

interface InsertUserRepo {
    fun insertUser(users: User): Flow<Long>

}