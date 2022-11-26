package com.madarsoft.users.domain.repository

import com.madarsoft.users.domain.models.User
import kotlinx.coroutines.flow.Flow

interface ShowUserRepo {

    fun getUsers(): Flow<List<User>>

}