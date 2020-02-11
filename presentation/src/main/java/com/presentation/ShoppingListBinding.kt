package com.presentation

import com.presentation.extension.toBrazilString
import com.presentation.extension.toBrazilianRealString
import java.math.BigInteger
import java.util.Date

data class ShoppingListBinding(
    val id: String? = null,
    val date: Date = Date(),
    val name: String = "",
    val items: List<GroceryItemBinding>? = mutableListOf()
) {
    val dateFormatted = date.toBrazilString()
    val total = items?.map { it.total }?.fold(BigInteger.ZERO, BigInteger::add)
    val totalFormatted = this.total?.toBrazilianRealString()
}