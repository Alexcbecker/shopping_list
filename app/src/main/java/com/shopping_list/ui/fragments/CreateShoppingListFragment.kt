package com.shopping_list.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.presentation.viewModel.CreateShoppingListViewModel
import com.shopping_list.R
import com.shopping_list.databinding.FragmentCreateShoppingListBinding
import com.shopping_list.ui.adapter.GroceryItemListAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CreateShoppingListFragment : Fragment() {

    private lateinit var _viewDateBinding: FragmentCreateShoppingListBinding
    //private val _createShoppingListViewModel by viewModel<CreateShoppingListViewModel>()
    private val _createShoppingListViewModel: CreateShoppingListViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(
            R.id.nav_main
        )
    })
    private val _groceryItemsAdapter by lazy {
        GroceryItemListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewDateBinding = FragmentCreateShoppingListBinding.inflate(inflater, container, false)
        _viewDateBinding.run {
            lifecycleOwner = this@CreateShoppingListFragment.viewLifecycleOwner
            viewModel = this@CreateShoppingListFragment._createShoppingListViewModel
            setupRecyclerView()
            setupListener()
        }
        return _viewDateBinding.root
    }

    private fun setupRecyclerView() {
        val recyclerView = _viewDateBinding.recyclerViewItemsList
        recyclerView.adapter = _groceryItemsAdapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }

    private fun setupListener() {
        _viewDateBinding.fabCreateAddGroceryItem.setOnClickListener {
            findNavController().navigate(R.id.action_createShoppingListFragment_to_groceryItemsListFragment)
        }
    }
}