package com.madarsoft.users.presentation.fragment

import android.app.Application
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.TypeTextAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.madarsoft.users.util.DataBindingIdlingResource
import com.madarsoft.users.util.monitorActivity
import com.madarsoft.users.utils.EspressoIdlingResource
import com.madarsoft.users.R
import com.madarsoft.users.data.db.UsersDatabaseRepo
import com.madarsoft.users.presentation.activities.MainActivity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class InsertUserFragmentTest{

    private lateinit var repository: UsersDatabaseRepo
    private lateinit var appContext: Application
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }


    @Test
    fun type_city_in_search_field() = runTest {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        onView(withId(R.id.inputEditText_username)).perform(TypeTextAction("youssef"))
        onView(withId(R.id.inputEditText_userage)).perform(TypeTextAction("25"))
        onView(withId(R.id.inputEditText_jobTitle)).perform(TypeTextAction("Android Developer"))
       onView(withId(R.id.maleRadioButton)).perform(click())
        onView(withId(R.id.maleRadioButton)).check(matches(isChecked()));
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.save_btn)).perform(click())




    }


}