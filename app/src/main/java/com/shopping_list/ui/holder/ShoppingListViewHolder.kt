package com.shopping_list.ui.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.presentation.ShoppingListBinding
import com.shopping_list.R

class ShoppingListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_list, parent, false)
) {
    private val nameView =
        itemView.findViewById<AppCompatTextView>(R.id.textView_shopping_list_name)

    private val dateView =
        itemView.findViewById<AppCompatTextView>(R.id.textView_shopping_list_date)

    private val quantityView =
        itemView.findViewById<AppCompatTextView>(R.id.textView_shopping_list_items_quantity)

    private var shoppingListBinding: ShoppingListBinding? = null

    fun bindTo(shoppingListBinding: ShoppingListBinding?) {
        this.shoppingListBinding = shoppingListBinding
        nameView.text = shoppingListBinding?.name ?: ""
        dateView.text = shoppingListBinding?.dateFormatted ?: ""
        quantityView.text = shoppingListBinding?.items
            .let { "${shoppingListBinding?.items?.size} items" }
    }
}