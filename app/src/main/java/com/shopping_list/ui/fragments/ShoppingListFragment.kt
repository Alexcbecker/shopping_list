package com.shopping_list.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.presentation.ShoppingListBinding
import com.presentation.viewModel.ShoppingListViewModel
import com.shopping_list.R
import com.shopping_list.databinding.FragmentShoppingListBinding
import com.shopping_list.ui.OnItemClickListener
import com.shopping_list.ui.adapter.ShoppingListAdapter
import com.shopping_list.ui.fragments.CreateShoppingListFragment.Companion.ARG_SHOPPING_LIST
import org.koin.android.viewmodel.ext.android.viewModel

class ShoppingListFragment : Fragment() {

    private lateinit var _viewDateBinding: FragmentShoppingListBinding
    private val shoppingListViewModel by viewModel<ShoppingListViewModel>()
    private val onItemClickListener = object
        : OnItemClickListener<ShoppingListBinding> {
        override fun onItemClick(data: ShoppingListBinding) {
            val bundle = bundleOf(ARG_SHOPPING_LIST to data)
            findNavController().navigate(R.id.action_shoppingListFragment_to_createShoppingListFragment, bundle)
        }
    }
    private val adapter = ShoppingListAdapter(onItemClickListener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewDateBinding = FragmentShoppingListBinding.inflate(inflater, container, false)
        _viewDateBinding.run {
            lifecycleOwner = this@ShoppingListFragment.viewLifecycleOwner
        }
        return _viewDateBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoppingListViewModel.shoppingLists.observe(this, Observer { pagedShoppingList ->
            pagedShoppingList?.let { render(pagedShoppingList) }
        })

        _viewDateBinding.fabCreateShoppingList.setOnClickListener {
            findNavController().navigate(R.id.action_shoppingListFragment_to_createShoppingListFragment)
        }

        setupRecyclerView()
    }

    private fun render(pagedNoteList: PagedList<ShoppingListBinding>) {
        val recyclerView = _viewDateBinding.recyclerViewShoppingLists
        adapter.submitList(pagedNoteList)
        if (pagedNoteList.isEmpty()) {
            recyclerView.visibility = View.GONE
        } else {
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = _viewDateBinding.recyclerViewShoppingLists
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }
}