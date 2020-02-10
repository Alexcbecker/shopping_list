package com.domain.repository

import com.domain.GroceryItem
import io.reactivex.Observable

interface IGroceryItemsRepository {

    fun getGroceryItemsList(): Observable<List<GroceryItem>>
}