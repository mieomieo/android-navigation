package com.example.navigation.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.navigation.model.User

@Dao
interface UserDao  {
    @Insert
    suspend fun registerUser(user: User)


}