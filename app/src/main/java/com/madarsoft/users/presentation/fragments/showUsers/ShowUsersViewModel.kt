package com.madarsoft.users.presentation.fragments.showUsers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madarsoft.users.domain.models.User
import com.madarsoft.users.domain.repository.ShowUserRepo
import com.madarsoft.users.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowUsersViewModel @Inject constructor(private val showUserRepo: ShowUserRepo) : ViewModel() {


    private  val _getUsersStatus : MutableStateFlow<Resource<List<User>>> = MutableStateFlow<Resource<List<User>>>(
        Resource.error(null,""))

    val getUsersStatus: StateFlow<Resource<List<User>>> =_getUsersStatus


    fun getUsers()
    {
        viewModelScope.launch {
            _getUsersStatus.value=Resource.loading(null)
            showUserRepo.getUsers().collect{
                if (it.isNotEmpty())
                {
                    _getUsersStatus.value=Resource.success(it)
                }else{
                    _getUsersStatus.value=Resource.error(it,"Users Table Empty")

                }
            }
        }

    }
}