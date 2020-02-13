package com.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.usecase.CreateShoppingListUseCase
import com.presentation.Event
import com.presentation.GroceryItemBinding
import com.presentation.ShoppingListBinding
import com.presentation.mapper.toDomain
import timber.log.Timber

class ManageShoppingListViewModel(
    application: Application,
    private val createShoppingListUseCase: CreateShoppingListUseCase
) : AndroidViewModel(application) {

    private val _shoppingList = MutableLiveData<ShoppingListBinding>()
        .apply { value = ShoppingListBinding() }
    val shoppingList: LiveData<ShoppingListBinding>
        get() = _shoppingList

    private val _groceryItem = MutableLiveData<GroceryItemBinding>()
    val groceryItem: LiveData<GroceryItemBinding>
        get() = _groceryItem

    private val _successEvent = MutableLiveData<Event<Unit>>()
    val successEvent: LiveData<Event<Unit>>
        get() = _successEvent

    fun createShoppingList() {
        _shoppingList.value?.let { shoppingList ->
            createShoppingListUseCase.execute(
                CreateShoppingListUseCase.Params(shoppingList.toDomain()),
                {
                    _successEvent.value = Event(Unit)
                },
                {
                    Timber.d(it)
                }
            )
        }
    }

    fun setShoppingListValue(shoppingListBinding: ShoppingListBinding?) {
        shoppingListBinding?.let {
            _shoppingList.value = shoppingListBinding
        }
    }

    fun setGroceryItemValue(groceryItemBinding: GroceryItemBinding) {
        _groceryItem.value = groceryItemBinding
    }

    fun addGroceryItemToShoppingList() {
        _groceryItem.value?.let {
            if (_shoppingList.value!!.items.filter { item -> item.id == it.id }.isEmpty()) {
                (_shoppingList.value?.items as MutableList).add(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        createShoppingListUseCase.dispose()
    }
}