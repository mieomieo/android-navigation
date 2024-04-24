package com.example.navigation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName ="user_table")
class User(@PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val email: String,
    val password: String,
):Parcelable