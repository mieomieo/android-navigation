package com.example.navigation.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.navigation.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): User?


}