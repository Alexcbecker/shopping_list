package com.presentation

import com.presentation.extension.toBrazilianRealString
import java.math.BigInteger

data class GroceryItemBinding(
    val id: String? = null,
    val name: String,
    val price: BigInteger,
    val quantity: Int
) {
    val priceFormatted = price.toBrazilianRealString()
}