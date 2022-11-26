package com.madarsoft.users.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.madarsoft.users.data.db.UserDataBaseImp
import com.madarsoft.users.data.db.UsersDatabase
import com.madarsoft.users.domain.models.User
import com.madarsoft.users.utils.Status

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Medium Test to test the repository
@MediumTest
class UsersDataBaseRepoTest {

    val user = getUser(1,"joe","Male","android developer","29")
    val user1 = getUser(2,"mohamed","Male","frontend developer","29")
    val user2 = getUser(3,"joe","Male","backend developer","29")
    private val remoteTasks = listOf(user, user1,user2).sortedBy { it.id }
    private lateinit var database: UsersDatabase
    private lateinit var repository: UserDataBaseImp




    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private fun getUser(id:Int, name :String, gender : String, jobTitle : String, age :String): User {
        return User(
            id = id,
            name = name,
            gender = gender,
            jobTitle = jobTitle,
            age = age
        )
    }


    @Before
    fun setup() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UsersDatabase::class.java
        ).allowMainThreadQueries().build()

        repository= UserDataBaseImp(database)
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun saveUser_retrievesUser() = runBlocking {
        // GIVEN - A new reminder saved in the database.

        val user = getUser(1,"joe","Male","android developer","29")
        repository.insertUser(user).collect{

        }

        // WHEN  - reminder retrieved by ID.
        val result = repository.findUser(user.name.toString()).collect{



            assertThat(it.id, `is`(user.id))
            assertThat(it.name, `is`(user.name))
            assertThat(it.age, `is`(user.age))
            assertThat(it.jobTitle, `is`(user.jobTitle))
            assertThat(it.gender, `is`(user.gender))

        }


        // THEN - Same reminder is returned.


    }

    @Test
    fun deleteAllUsers_getUserById() = runBlocking {


        val user = getUser(1,"joe","Male","android developer","29")
        repository.insertUser(user).collect()
        repository.deleteUsers()
        val result = repository.findUser(user.name.toString())
        result.collect{ assertThat(it.id, `is`(1))


            }
        }





    }
