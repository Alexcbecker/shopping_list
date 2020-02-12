package com.presentation

import com.presentation.extension.toBrazilianRealString
import java.math.BigInteger

data class GroceryItemBinding(
    var id: String? = null,
    val name: String,
    val price: BigInteger,
    var quantity: String
) {
    var priceFormatted = price.toBrazilianRealString()
    val total get() = price.multiply(BigInteger.valueOf(quantity.toLong()))
    val totalFormatted get() = this.total?.toBrazilianRealString()
}