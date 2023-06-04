package com.alwan.thelorryresto.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alwan.thelorryresto.databinding.ItemRestaurantPreviewBinding
import com.alwan.core.util.loadImage

class RestaurantPreviewAdapter(private val onRestaurantPreviewClicked: (String) -> Unit) :
    ListAdapter<String, RestaurantPreviewAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ItemRestaurantPreviewBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,
            false,
        )
    )


    override fun onBindViewHolder(holder: RestaurantPreviewAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemRestaurantPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: String) = binding.apply {

            ivRestaurantPreview.loadImage("")
            root.setOnClickListener { onRestaurantPreviewClicked(restaurant) }
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