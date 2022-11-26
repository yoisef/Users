package com.madarsoft.users.presentation.fragments.insertUser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madarsoft.users.domain.models.User
import com.madarsoft.users.domain.repository.InsertUserRepo
import com.madarsoft.users.utils.Resource
import com.madarsoft.users.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertUserViewModel @Inject constructor(private val insertUserRepo: InsertUserRepo) : ViewModel() {


    private val _insertStatus: MutableSharedFlow<Resource<Long>> = MutableSharedFlow(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val insertStatus :SharedFlow<Resource<Long>> =_insertStatus

    fun insertUser(user: User)
    {
        viewModelScope.launch {
            _insertStatus.emit(Resource.loading(null))
            insertUserRepo.insertUser(user).collect {

                //Check Insertion
                if (it==-1L)
                {
                   _insertStatus.emit(Resource.error(it,"Inserted Failed"))

                }else{
                  _insertStatus.emit(Resource.success(it))
                }

            }
        }

    }




}