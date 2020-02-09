package com.domain

import java.math.BigInteger

data class GroceryItems(
    val id: String,
    val name: String,
    val price: BigInteger,
    val quantity: Int
)