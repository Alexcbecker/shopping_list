package com.data

import com.domain.GroceryItem
import io.reactivex.Observable

interface IGroceryItemsRemoteDataSource {

    fun getGroceryItemsList(): Observable<List<GroceryItem>>
}