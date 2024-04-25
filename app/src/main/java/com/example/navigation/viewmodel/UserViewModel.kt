package com.example.navigation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.navigation.model.User
import com.example.navigation.repository.UserRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class UserViewModel (app : Application):ViewModel(){
    private val userRepository : UserRepository = UserRepository(app)
    class UserViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }
    fun registerUser(user: User) = viewModelScope.launch {
        userRepository.registerUser(user)
    }

}