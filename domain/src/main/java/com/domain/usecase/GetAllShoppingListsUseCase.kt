package com.domain.usecase

import com.domain.repository.IShoppingListRepository

class GetAllShoppingListsUseCase(
    private val shoppingRepository: IShoppingListRepository
) {
    fun getAllShoppingLists() = shoppingRepository.getAllShoppingLists()
}