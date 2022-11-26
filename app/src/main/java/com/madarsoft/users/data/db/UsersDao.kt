package com.madarsoft.users.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.madarsoft.users.domain.models.User


@Dao
interface UsersDao {




    @Query("SELECT * FROM users")
    suspend fun getUsers() : List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : User) : Long


    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM users WHERE name LIKE :name || '%'")
    suspend fun findUser(name:String): User
}