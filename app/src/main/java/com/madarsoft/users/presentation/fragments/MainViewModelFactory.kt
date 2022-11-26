package com.madarsoft.users.presentation.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madarsoft.users.domain.repository.InsertUserRepo
import com.madarsoft.users.domain.repository.ShowUserRepo
import com.madarsoft.users.presentation.fragments.insertUser.InsertUserViewModel
import com.madarsoft.users.presentation.fragments.showUsers.ShowUsersViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val insertUserRepo: InsertUserRepo, private val showUserRepo: ShowUserRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(InsertUserViewModel::class.java)) {
            return InsertUserViewModel( insertUserRepo = insertUserRepo ) as T
        }
        if (modelClass.isAssignableFrom(ShowUsersViewModel::class.java)) {
            return ShowUsersViewModel(showUserRepo = showUserRepo) as T
        }
        throw IllegalArgumentException("Unknown class name")

    }



}