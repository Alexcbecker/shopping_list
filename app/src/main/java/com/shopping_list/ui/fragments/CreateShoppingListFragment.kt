package com.shopping_list.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shopping_list.databinding.FragmentCreateShoppingListBinding

class CreateShoppingListFragment : Fragment() {

    private lateinit var _viewDateBinding: FragmentCreateShoppingListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewDateBinding = FragmentCreateShoppingListBinding.inflate(inflater, container, false)
        _viewDateBinding.run {
            lifecycleOwner = this@CreateShoppingListFragment.viewLifecycleOwner
        }
        return _viewDateBinding.root
    }
}