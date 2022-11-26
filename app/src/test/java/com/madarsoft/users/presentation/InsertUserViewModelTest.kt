package com.madarsoft.users.presentation

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.madarsoft.db.FakeDataSource
import com.madarsoft.users.domain.models.User
import com.madarsoft.users.presentation.fragments.insertUser.InsertUserViewModel
import com.madarsoft.users.utils.Resource
import com.udacity.project4.locationreminders.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.pauseDispatcher

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(maxSdk = Build.VERSION_CODES.P)
@ExperimentalCoroutinesApi
class InsertUserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = CoroutineRule()

    private lateinit var fakeRepo: FakeDataSource
    private lateinit var mainViewModel: InsertUserViewModel

    @Before
    fun createRepository() {

        fakeRepo = FakeDataSource()
        mainViewModel = InsertUserViewModel(fakeRepo)
    }
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
    fun insertUser() {
       runBlocking {
           val user = getUser(1,"joe","Male","android developer","29")

           fakeRepo.insertUser(user).collect{
               MatcherAssert.assertThat(it, CoreMatchers.`is`(1L))

           }



       }


    }

}