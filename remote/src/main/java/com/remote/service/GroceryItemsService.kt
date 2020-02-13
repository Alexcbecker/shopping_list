package com.remote.service

import com.remote.GroceryItemEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface GroceryItemsService {

    @GET("groceryItems")
    fun getGroceryItems(): Observable<List<GroceryItemEntity>>
}