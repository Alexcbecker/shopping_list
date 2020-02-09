package com.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: String? = null,
    val date: Date,
    val name: String
)