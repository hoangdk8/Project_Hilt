package com.example.project_hilt_mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val username: String
)
