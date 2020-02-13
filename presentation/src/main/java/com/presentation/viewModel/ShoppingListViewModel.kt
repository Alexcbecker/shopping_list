package com.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.domain.usecase.GetAllShoppingListsUseCase
import com.presentation.ShoppingListBinding
import com.presentation.mapper.fromDomain

private const val PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE_HINT = 20

class ShoppingListViewModel(
    application: Application,
    private val getAllShoppingListsUseCase: GetAllShoppingListsUseCase
) : AndroidViewModel(application) {

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setPageSize(PAGE_SIZE)
        .build()


    val shoppingLists: LiveData<PagedList<ShoppingListBinding>> =
        LivePagedListBuilder(
            getAllShoppingListsUseCase.getAllShoppingLists()
                .map { it.fromDomain() }, PAGE_SIZE
        ).build()

}