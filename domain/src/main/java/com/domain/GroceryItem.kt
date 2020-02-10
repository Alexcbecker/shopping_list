package com.domain

import java.math.BigInteger

data class GroceryItem(
    val id: String? = null,
    val name: String,
    val price: BigInteger,
    val quantity: Int = 0
)