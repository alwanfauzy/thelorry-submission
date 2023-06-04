package com.alwan.thelorryresto.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alwan.core.domain.model.Food
import com.alwan.core.util.loadImage
import com.alwan.thelorryresto.databinding.ItemRestaurantBinding

class RestaurantAdapter(
    private val onRestaurantClicked: (Food, ItemRestaurantBinding) -> Unit,
) :
    ListAdapter<Food, RestaurantAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ItemRestaurantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Food) = binding.apply {
            tvRestaurant.text = restaurant.title
            ivRestaurant.loadImage(restaurant.image)

            root.setOnClickListener { onRestaurantClicked(restaurant, binding) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(
                oldItem: Food,
                newItem: Food
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Food,
                newItem: Food
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}