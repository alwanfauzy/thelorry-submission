package com.alwan.thelorryresto.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwan.core.data.util.onError
import com.alwan.core.data.util.onLoading
import com.alwan.core.data.util.onSuccess
import com.alwan.core.domain.model.Food
import com.alwan.core.util.hide
import com.alwan.core.util.show
import com.alwan.core.util.showToast
import com.alwan.thelorryresto.R
import com.alwan.thelorryresto.databinding.ActivityMainBinding
import com.alwan.thelorryresto.databinding.ItemRestaurantBinding
import com.alwan.thelorryresto.presentation.detail.DetailActivity
import com.alwan.thelorryresto.presentation.detail.DetailActivity.Companion.EXTRA_ID
import com.alwan.thelorryresto.presentation.main.adapter.RestaurantAdapter
import com.alwan.thelorryresto.presentation.main.adapter.RestaurantCategoryAdapter
import com.alwan.thelorryresto.util.HorizontalMarginItemDecoration
import com.alwan.thelorryresto.util.VerticalMarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private val categories = listOf(
        "asian",
        "western",
        "japanese",
        "fast_food",
        "korean",
        "thai"
    )
    private val restaurantAdapter = RestaurantAdapter { data, binding ->
        handleRestaurantClicked(data, binding)
    }
    private val restaurantCategoryAdapter = RestaurantCategoryAdapter {
        handleRestaurantCategoryClicked(it)
    }.apply {
        submitList(categories)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            // TODO handle back on click
        }
        setupRv()
        setupVm(categories[0])
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRv() {
        binding.rvCategories.apply {
            adapter = restaurantCategoryAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(
                HorizontalMarginItemDecoration(
                    width = resources.getDimension(R.dimen.spacing_8dp).roundToInt(),
                )
            )
            hasFixedSize()
        }
        binding.rvRestaurants.apply {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                VerticalMarginItemDecoration(
                    height = resources.getDimension(R.dimen.spacing_8dp).roundToInt(),
                    width = resources.getDimension(R.dimen.spacing_16dp).roundToInt()
                )
            )
            hasFixedSize()
        }
    }

    private fun setupVm(category: String) {
        binding.llFilter.setOnClickListener {
            // TODO handle filter on click
        }

        mainViewModel.getRestaurantListByCategory(category).observe(this) { result ->
            result.onLoading {
                showRestaurantLoading()
                hideRestaurantError()
            }.onSuccess {
                hideRestaurantLoading()

                if (it.isNullOrEmpty())
                    showRestaurantError(getString(R.string.empty_data))
                else
                    restaurantAdapter.submitList(it)
            }.onError { message, _ ->
                hideRestaurantLoading()
                showRestaurantError(message)
                showToast(message)
            }
        }
    }

    private fun handleRestaurantClicked(restaurant: Food, binding: ItemRestaurantBinding) {
        val optionsCompat: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                binding.tvRestaurant,
                "restaurantName"
            )
        val detailIntent = Intent(this, DetailActivity::class.java).apply {
            putExtra(EXTRA_ID, restaurant.id)
        }
        startActivity(detailIntent, optionsCompat.toBundle())
    }

    private fun handleRestaurantCategoryClicked(category: String) {
        setupVm(category)
    }

    private fun showRestaurantLoading() = binding.apply {
        rvRestaurants.hide()
        pbRestaurants.show()
    }

    private fun hideRestaurantLoading() = binding.apply {
        rvRestaurants.show()
        pbRestaurants.hide()
    }


    private fun showRestaurantError(message: String?) = binding.apply {
        tvError.text = message ?: getString(R.string.error)
        tvError.show()
        rvRestaurants.hide()
    }

    private fun hideRestaurantError() = binding.apply {
        tvError.hide()
    }
}