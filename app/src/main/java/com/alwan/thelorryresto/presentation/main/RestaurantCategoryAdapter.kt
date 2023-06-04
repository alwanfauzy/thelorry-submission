package com.alwan.thelorryresto.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alwan.thelorryresto.databinding.ItemRestaurantCategoryBinding

class RestaurantCategoryAdapter(private val onCategoriesClicked: (String) -> Unit) :
    ListAdapter<String, RestaurantCategoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ItemRestaurantCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RestaurantCategoryAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemRestaurantCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) = binding.apply {
            tvCategory.text = category

            tvCategory.setOnClickListener { onCategoriesClicked(category) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}