package com.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.usecase.GetGroceryItemsListUseCase
import com.presentation.GroceryItemBinding
import com.presentation.mapper.fromDomain
import timber.log.Timber

class GetGroceryItemsListViewModel(
    application: Application,
    private val getGroceryItemsListUseCase: GetGroceryItemsListUseCase
) : AndroidViewModel(application) {

    private val _groceryItemList = MutableLiveData<List<GroceryItemBinding>>().apply { value = mutableListOf() }
    val groceryItemList: LiveData<List<GroceryItemBinding>>
        get() = _groceryItemList

    fun getGroceryItemsList() {
        getGroceryItemsListUseCase.execute(
            Unit,
            {
                _groceryItemList.value = it.map { groceryItem ->
                    groceryItem.fromDomain()
                }
            },
            {
                Timber.d(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        getGroceryItemsListUseCase.dispose()
    }
}