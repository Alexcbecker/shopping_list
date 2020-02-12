package com.shopping_list.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.presentation.GroceryItemBinding
import com.shopping_list.databinding.ItemBasicGroceryItemBinding

class GroceryItemViewHolder(private val itemBinding: ItemBasicGroceryItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(groceryItemBinding: GroceryItemBinding) {
        itemBinding.groceryItem = groceryItemBinding
    }
}