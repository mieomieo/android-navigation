package com.example.navigation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.navigation.model.User
import com.example.navigation.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class UserViewModel(app: Application) : ViewModel() {
    private val userRepository: UserRepository = UserRepository(app)
    private val _loginResult = MutableLiveData<User?>()
    val loginResult: LiveData<User?> = _loginResult
    private val _isEmailExisted = MutableLiveData<Int>()
    val isEmailExisted: LiveData<Int> = _isEmailExisted

    class UserViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }

    fun registerUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.registerUser(user)
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.loginUser(email, password)
            _loginResult.postValue(user)
        }
    }

    fun checkUniqueEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO){
            val checkUniqueEmail = userRepository.checkUniqueEmail(email)
            _isEmailExisted.postValue(checkUniqueEmail)
        }
    }

    fun changePassword(email: String, currentPassword: String, newPassword: String){
        viewModelScope.launch (Dispatchers.IO){

        }
    }

}