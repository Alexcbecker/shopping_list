package com.shopping_list.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.presentation.viewModel.CreateShoppingListViewModel
import com.shopping_list.R
import com.shopping_list.databinding.FragmentSelectQuantityGroceryItemBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class GroceryItemQuantityFragment : Fragment() {

    private lateinit var _viewDateBinding: FragmentSelectQuantityGroceryItemBinding
    private val _createShoppingListViewModel: CreateShoppingListViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(
            R.id.nav_main
        )
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewDateBinding = FragmentSelectQuantityGroceryItemBinding.inflate(inflater, container, false)
        _viewDateBinding.run {
            lifecycleOwner = this@GroceryItemQuantityFragment.viewLifecycleOwner
            viewModel = this@GroceryItemQuantityFragment._createShoppingListViewModel
           setupListener()
        }
        return _viewDateBinding.root
    }

    private fun setupListener() {
        _viewDateBinding.buttonAddGroceryItem.setOnClickListener {
            _createShoppingListViewModel.addGroceryItemToShoppingList()
            findNavController().popBackStack(R.id.createShoppingListFragment, false)
        }
    }
}