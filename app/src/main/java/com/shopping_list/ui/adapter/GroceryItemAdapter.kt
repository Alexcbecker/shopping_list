package com.shopping_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.presentation.GroceryItemBinding
import com.shopping_list.databinding.ItemBasicGroceryItemBinding
import com.shopping_list.ui.OnItemClickListener
import com.shopping_list.ui.holder.GroceryItemViewHolder

class GroceryItemAdapter(private val onItemClickListener: OnItemClickListener<GroceryItemBinding>) :
    RecyclerView.Adapter<GroceryItemViewHolder>() {

    private var _groceryItemsList: List<GroceryItemBinding> = emptyList()

    fun replaceData(groceryItemsList: List<GroceryItemBinding>) {
        _groceryItemsList = groceryItemsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryItemViewHolder {
        val viewBinding = ItemBasicGroceryItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GroceryItemViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = _groceryItemsList.size

    override fun onBindViewHolder(holder: GroceryItemViewHolder, position: Int) {
        val groceryItem = _groceryItemsList[position]
        holder.bind(groceryItem)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(groceryItem)
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("basicGroceryItem")
        fun setGroceryItems(recyclerView: RecyclerView, groceryItems: MutableList<GroceryItemBinding>) {
            with(recyclerView.adapter as GroceryItemAdapter) {
                replaceData(groceryItems)
            }
        }
    }
}