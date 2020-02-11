package com.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.usecase.CreateShoppingListUseCase
import com.presentation.Event
import com.presentation.ShoppingListBinding
import com.presentation.mapper.toDomain
import timber.log.Timber

class CreateShoppingListViewModel(
    application: Application,
    private val createShoppingListUseCase: CreateShoppingListUseCase
) : AndroidViewModel(application) {

    private val _shoppingList = MutableLiveData<ShoppingListBinding>()
    val shoppingList: LiveData<ShoppingListBinding>
        get() = _shoppingList

    private val _successEvent = MutableLiveData<Event<Unit>>()
    val successEvent: LiveData<Event<Unit>>
        get() = _successEvent

    fun createShoppingList() {
        createShoppingListUseCase.execute(
            CreateShoppingListUseCase.Params(_shoppingList.value!!.toDomain()),
            {
                _successEvent.value = Event(Unit)
            },
            {
                Timber.d(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        createShoppingListUseCase.dispose()
    }
}