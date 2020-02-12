package com.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.usecase.CreateShoppingListUseCase
import com.domain.usecase.DeleteShoppingListUseCase
import com.presentation.Event
import com.presentation.GroceryItemBinding
import com.presentation.ShoppingListBinding
import com.presentation.mapper.toDomain
import timber.log.Timber

class DeleteShoppingListViewModel(
    application: Application,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase
) : AndroidViewModel(application) {

    private val _successEvent = MutableLiveData<Event<Unit>>()
    val successEvent: LiveData<Event<Unit>>
        get() = _successEvent

    fun deleteShoppingList(id: String) {
        deleteShoppingListUseCase.execute(
            DeleteShoppingListUseCase.Params(id),
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
        deleteShoppingListUseCase.dispose()
    }
}