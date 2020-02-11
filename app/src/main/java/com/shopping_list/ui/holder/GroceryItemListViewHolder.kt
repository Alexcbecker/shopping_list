package com.shopping_list.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.presentation.GroceryItemBinding
import com.shopping_list.databinding.ItemShoppingListGroceryItemBinding

class GroceryItemListViewHolder(private val itemBinding: ItemShoppingListGroceryItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(groceryItemBinding: GroceryItemBinding) {
        itemBinding.groceryItem = groceryItemBinding
    }
}