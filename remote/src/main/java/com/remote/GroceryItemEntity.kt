package com.remote

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class GroceryItemEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: BigInteger
)