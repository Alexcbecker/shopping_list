package com.shopping_list.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.presentation.viewModel.ManageShoppingListViewModel
import com.shopping_list.R
import com.shopping_list.databinding.FragmentSelectQuantityGroceryItemBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class GroceryItemQuantityFragment : Fragment() {

    private lateinit var _viewDateBinding: FragmentSelectQuantityGroceryItemBinding
    private val _manageShoppingListViewModel: ManageShoppingListViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(
            R.id.nav_main
        )
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewDateBinding = FragmentSelectQuantityGroceryItemBinding.inflate(inflater, container, false)
        _viewDateBinding.run {
            lifecycleOwner = this@GroceryItemQuantityFragment.viewLifecycleOwner
            viewModel = this@GroceryItemQuantityFragment._manageShoppingListViewModel
            setupListeners()
            _viewDateBinding.edittextGroceryItemQuantity.requestFocus()
        }
        return _viewDateBinding.root
    }

    private fun setupListeners() {
        _viewDateBinding.buttonAddGroceryItem.setOnClickListener {
            _viewDateBinding.edittextGroceryItemQuantity.clearFocus()
            _viewDateBinding.constraintLayoutFragmentSelectQuantity.requestFocus()
            val viewText = _viewDateBinding.edittextGroceryItemQuantity.text.toString()
            if (viewText.isBlank() || viewText.equals("0")) {
                showValidationError()
            } else {
                _manageShoppingListViewModel.addGroceryItemToShoppingList()
                findNavController().popBackStack(R.id.manageShoppingListFragment, false)

            }
        }

        _viewDateBinding.edittextGroceryItemQuantity.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) clearFieldErrors()
        }
    }

    private fun showValidationError() {
        _viewDateBinding.textInputLayoutGroceryItemQuantity.isErrorEnabled = true
        _viewDateBinding.textInputLayoutGroceryItemQuantity.error = getString(R.string.validation_invalid_value)
    }

    private fun clearFieldErrors() {
        _viewDateBinding.textInputLayoutGroceryItemQuantity.isErrorEnabled = false
    }
}