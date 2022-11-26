package com.madarsoft.users.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import com.madarsoft.users.data.db.UsersDatabase
import com.madarsoft.users.domain.models.User

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Test

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Unit test the DAO
@SmallTest

class UsersDaoTest {

//    TODO: Add testing implementation to the RemindersDao.kt

    private lateinit var database: UsersDatabase

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UsersDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() = database.close()


     private fun getUser(id:Int, name :String, gender : String, jobTitle : String, age :String): User {
        return User(
            id = id,
            name = name,
            gender = gender,
            jobTitle = jobTitle,
            age = age
           )
    }

    @Test
    fun insertUserAndFindByName() = runTest {
        // GIVEN - Insert a reminder.
        val user = getUser(1,"joe","Male","android developer","29")
        database.getDao().insertUser(user)

        // WHEN - Get the reminder by id from the database.
        val loaded = database.getDao().findUser(user.name.toString())

        // THEN - The loaded data contains the expected values.
        assertThat<User>(loaded as User, notNullValue())
        assertThat(loaded.id, `is`(user.id))
        assertThat(loaded.name, `is`(user.name))
        assertThat(loaded.age, `is`(user.age))
        assertThat(loaded.jobTitle, `is`(user.jobTitle))
    }

    @Test
    fun deleteAnd_Users() = runTest {
        // GIVEN - Insert a reminder.
        val user = getUser(1,"joe","Male","android developer","29")
        val user1 = getUser(2,"mohamed","Male","frontend developer","29")
        val user2 = getUser(3,"kraim","Male","backend developer","29")

        database.getDao().deleteAllUsers()
        val num1= database.getDao().getUsers().size

        assertThat(num1,`is`(0))

        database.getDao().insertUser(user)
        database.getDao().insertUser(user1)
        database.getDao().insertUser(user2)

        val num= database.getDao().getUsers().size

        assertThat(num,`is`(3))

    }

}
