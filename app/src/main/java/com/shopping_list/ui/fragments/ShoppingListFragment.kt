package com.shopping_list.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shopping_list.R
import com.shopping_list.databinding.FragmentShoppingListBinding

class ShoppingListFragment : Fragment() {

    private lateinit var _viewDateBinding: FragmentShoppingListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewDateBinding = FragmentShoppingListBinding.inflate(inflater, container, false)
        _viewDateBinding.run {
            lifecycleOwner = this@ShoppingListFragment.viewLifecycleOwner
        }
        return _viewDateBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewDateBinding.fabCreateShoppingList.setOnClickListener {
            findNavController().navigate(R.id.action_shoppingListFragment_to_createShoppingListFragment)
        }
    }
}