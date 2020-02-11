package com.presentation

import com.presentation.extension.toBrazilString
import com.presentation.extension.toBrazilianRealString
import java.math.BigInteger
import java.util.Date

data class ShoppingListBinding(
    val id: String? = null,
    val date: Date = Date(),
    val name: String = "",
    val items: List<GroceryItemBinding>? = null
) {
    val dateFormatted = date.toBrazilString()
    val total = items?.map {
        it.price.multiply(BigInteger.valueOf(it.quantity.toLong())) }
        ?.fold(BigInteger.ZERO, BigInteger::add)
    val totalFormatted = this.total?.toBrazilianRealString()
}