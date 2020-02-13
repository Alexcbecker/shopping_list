package com.shopping_list.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.presentation.GroceryItemBinding
import com.presentation.viewModel.ManageShoppingListViewModel
import com.presentation.viewModel.GetGroceryItemsListViewModel
import com.shopping_list.R
import com.shopping_list.databinding.FragmentGroceryItemsListBinding
import com.shopping_list.ui.OnItemClickListener
import com.shopping_list.ui.adapter.GroceryItemAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class GroceryItemsListFragment : Fragment() {

    private lateinit var _viewDateBinding: FragmentGroceryItemsListBinding
    private val _getGroceryItemsListViewModel by viewModel<GetGroceryItemsListViewModel>()
    private val _manageShoppingListViewModel: ManageShoppingListViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(
            R.id.nav_main
        )
    })
    private val onItemClickListener = object : OnItemClickListener<GroceryItemBinding> {
        override fun onItemClick(data: GroceryItemBinding) {
            _manageShoppingListViewModel.setGroceryItemValue(data)
            findNavController().navigate(R.id.action_groceryItemsListFragment_to_groceryItemsQuantityFragment)
        }
    }
    private val _groceryItemsAdapter by lazy {
        GroceryItemAdapter(onItemClickListener)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewDateBinding = FragmentGroceryItemsListBinding.inflate(inflater, container, false)
        _viewDateBinding.run {
            lifecycleOwner = this@GroceryItemsListFragment.viewLifecycleOwner
            viewModel = this@GroceryItemsListFragment._getGroceryItemsListViewModel
            setupRecyclerView()
        }
        return _viewDateBinding.root
    }

    override fun onResume() {
        super.onResume()
        _getGroceryItemsListViewModel.getGroceryItemsList()
    }

    private fun setupRecyclerView() {
        val recyclerView = _viewDateBinding.recyclerViewGroceryItemsList
        recyclerView.adapter = _groceryItemsAdapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}