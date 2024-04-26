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

    @Query("SELECT COUNT(*) FROM user_table WHERE email = :email")
    suspend fun checkUniqueEmail(email: String): Int
    @Query("UPDATE user_table SET password = :newPassword WHERE email = :email AND password = :currentPassword")
    suspend fun changePassword(email: String, currentPassword: String, newPassword: String): Int
}