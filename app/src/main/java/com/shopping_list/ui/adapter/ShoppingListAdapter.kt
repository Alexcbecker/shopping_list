package com.shopping_list.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.presentation.ShoppingListBinding
import com.shopping_list.ui.OnItemClickListener
import com.shopping_list.ui.holder.ShoppingListViewHolder

class ShoppingListAdapter(
    private val onItemClickListener: OnItemClickListener<ShoppingListBinding>
) : PagedListAdapter<ShoppingListBinding, ShoppingListViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val shoppingListBinding = getItem(position)

        with(holder) {
            bindTo(shoppingListBinding)
            shoppingListBinding?.let {
                itemView.setOnClickListener {
                    onItemClickListener.onItemClick(shoppingListBinding)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder =
        ShoppingListViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ShoppingListBinding>() {
            override fun areItemsTheSame(oldItem: ShoppingListBinding, newItem: ShoppingListBinding): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ShoppingListBinding, newItem: ShoppingListBinding): Boolean =
                oldItem == newItem
        }
    }
}