package com.task.healthylife.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.task.healthylife.firebase.FirebaseAuth
import com.task.healthylife.model.UserInformation
import com.task.healthylife.room.UserInformationDao
import com.task.healthylife.room.UserInformationDatabase
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val userInformationDao: UserInformationDao =
        UserInformationDatabase.getDatabase(application).userInformationDao()

    val users :Flow<List<UserInformation>> = userInformationDao.getAllUsers()


    fun register(userName: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            FirebaseAuth.register(
                userName = userName,
                password = password,
                onResult = onResult
            )
        }
    }

    fun login(userName: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            FirebaseAuth.login(
                userName = userName,
                password = password,
                onResult = onResult
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            FirebaseAuth.logout()
        }
    }
    fun insertUser(userName: String,password: String){
        viewModelScope.launch {
            userInformationDao.insertUser(UserInformation(username = userName, password = password))
        }
    }


}