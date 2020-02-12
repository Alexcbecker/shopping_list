package com.presentation

import com.presentation.extension.toBrazilString
import com.presentation.extension.toBrazilianRealString
import java.math.BigInteger
import java.util.Date

data class ShoppingListBinding(
    var id: String? = null,
    var date: Date = Date(),
    var name: String? = null,
    var items: List<GroceryItemBinding> = mutableListOf()
) {
    var dateFormatted = date.toBrazilString()
    val total get() = items.map { it.total }.fold(BigInteger.ZERO, BigInteger::add)
    val totalFormatted get() = total?.toBrazilianRealString()
}