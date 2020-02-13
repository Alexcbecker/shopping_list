package com.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.usecase.AddItemsToShoppingListUseCase
import com.domain.usecase.CreateShoppingListUseCase
import com.domain.usecase.EditShoppingListUseCase
import com.presentation.Event
import com.presentation.GroceryItemBinding
import com.presentation.ShoppingListBinding
import com.presentation.mapper.toDomain
import timber.log.Timber

@Suppress("IMPLICIT_CAST_TO_ANY")
class ManageShoppingListViewModel(
    application: Application,
    private val createShoppingListUseCase: CreateShoppingListUseCase,
    private val addItemsToShoppingListUseCase: AddItemsToShoppingListUseCase,
    private val editShoppingListUseCase: EditShoppingListUseCase
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

    var isEditing = false

    fun dispatchEvent() {
        if (isEditing) editShoppingList()
        else createShoppingList()
    }

    private fun createShoppingList() {
        _shoppingList.value?.let { shoppingList ->
            createShoppingListUseCase.execute(
                CreateShoppingListUseCase.Params(shoppingList.toDomain()),
                { shoppingListId ->
                    if (_shoppingList.value?.items != null) {
                        addItemsToShoppingList(shoppingListId)
                    } else {
                        clearData()
                        _successEvent.value = Event(Unit)
                    }
                },
                {
                    Timber.d(it)
                }
            )
        }
    }

    private fun addItemsToShoppingList(id: Long) {
        _shoppingList.value?.id = id.toString()
        addItemsToShoppingListUseCase.execute(
            AddItemsToShoppingListUseCase.Params(_shoppingList.value!!.toDomain()),
            {
                clearData()
                _successEvent.value = Event(Unit)
            },
            {
                Timber.d(it)
            }
        )
    }

    private fun editShoppingList() {
        _shoppingList.value?.let { shoppingList ->
            editShoppingListUseCase.execute(
                EditShoppingListUseCase.Params(shoppingList.toDomain()),
                {
                    clearData()
                    _successEvent.value = Event(Unit)
                },
                {
                    Timber.d(it)
                }
            )
        }
    }

    private fun editGroceryItemsOfTheShoppingList() {

    }

    fun setShoppingListValue(shoppingListBinding: ShoppingListBinding?) {
        shoppingListBinding?.let {
            isEditing = true
            _shoppingList.value = shoppingListBinding
        }
    }

    fun setGroceryItemValue(groceryItemBinding: GroceryItemBinding) {
        _groceryItem.value = groceryItemBinding
    }

    fun addGroceryItemToShoppingList() {
        _groceryItem.value?.let {
            if (_shoppingList.value!!.items.any { item -> item.id == it.id }) {
                (_shoppingList.value?.items as MutableList).set(
                    _shoppingList.value?.items!!.indexOfFirst { item -> item.id == it.id },
                    it
                )
            } else {
                (_shoppingList.value?.items as MutableList).add(it)
            }
        }
    }

    fun clearData() {
        _shoppingList.value = ShoppingListBinding()
        isEditing = false
    }

    override fun onCleared() {
        super.onCleared()
        createShoppingListUseCase.dispose()
        editShoppingListUseCase.dispose()
        addItemsToShoppingListUseCase.dispose()
    }
}