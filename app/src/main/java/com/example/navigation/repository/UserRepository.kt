package com.example.navigation.repository

import android.app.Application
import com.example.navigation.dao.UserDao
import com.example.navigation.database.UserDatabase
import com.example.navigation.model.User

class UserRepository (app:Application){
    private val userDao : UserDao
    init{
        val userDatabase:UserDatabase = UserDatabase.getInstance(app)
        userDao = userDatabase.getUserDao()
    }
    suspend fun registerUser(user: User) = userDao.registerUser(user)
    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}