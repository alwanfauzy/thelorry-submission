package com.alwan.thelorryresto.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwan.thelorryresto.R
import com.alwan.thelorryresto.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val restaurantPreviewAdapter =
        RestaurantPreviewAdapter { handleRestaurantPreviewClicked(it) }
    private val commentAdapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.ivCopy.setOnClickListener { }
        binding.ivSend.setOnClickListener { }
        binding.llNavigate.setOnClickListener { }

        populateDetail("Restaurant")
        setupRv()
        setupVm()
    }

    private fun populateDetail(restaurant: String) = binding.apply {
        tvAddress.text = "Lot 3, Persiaran Klang Sentral 2, Klang Sentral, 41050 Klang, Selangor"
        tvDescription.text = getString(R.string.lorem_ipsum)
        tvTitle.text = restaurant
    }

    private fun setupRv() {
        binding.rvPreviewRestaurant.apply {
            layoutManager =
                LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = restaurantPreviewAdapter
        }

        binding.rvComment.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = commentAdapter
        }
    }

    private fun setupVm() {
        restaurantPreviewAdapter.submitList(listOf("aoe", "aoe", "aoe", "aoe", "aoe"))
        commentAdapter.submitList(listOf("aoe", "aoe", "aoe", "aoe", "aoe"))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun handleRestaurantPreviewClicked(restaurantPreview: String) {

    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}