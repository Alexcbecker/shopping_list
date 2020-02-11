package com.shopping_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.presentation.GroceryItemBinding
import com.shopping_list.databinding.ItemShoppingListGroceryItemBinding
import com.shopping_list.ui.holder.GroceryItemListViewHolder
import kotlinx.android.synthetic.main.item_shopping_list_grocery_item.view.*

class GroceryItemListAdapter : RecyclerView.Adapter<GroceryItemListViewHolder>() {

    private var _groceryItemsList: List<GroceryItemBinding> = emptyList()

    fun replaceData(groceryItemsList: List<GroceryItemBinding>) {
        _groceryItemsList = groceryItemsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryItemListViewHolder {
        val viewBinding = ItemShoppingListGroceryItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GroceryItemListViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = _groceryItemsList.size

    override fun onBindViewHolder(holder: GroceryItemListViewHolder, position: Int) {
        val groceryItem = _groceryItemsList[position]
        holder.bind(groceryItem)
        holder.itemView.view_remove_item.setOnClickListener {
            (_groceryItemsList as MutableList).removeAt(position)
            notifyDataSetChanged()
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("groceryItems")
        fun setGroceryItemsList(recyclerView: RecyclerView, groceryItems: MutableList<GroceryItemBinding>) {
            with(recyclerView.adapter as GroceryItemListAdapter) {
                replaceData(groceryItems)
            }
        }
    }
}