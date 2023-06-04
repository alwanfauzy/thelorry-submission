package com.alwan.thelorryresto.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwan.core.data.remote.request.SendCommentRequest
import com.alwan.core.data.util.onError
import com.alwan.core.data.util.onLoading
import com.alwan.core.data.util.onSuccess
import com.alwan.core.domain.model.FoodDetail
import com.alwan.core.util.addToClipboard
import com.alwan.core.util.hide
import com.alwan.core.util.show
import com.alwan.core.util.showToast
import com.alwan.thelorryresto.R
import com.alwan.thelorryresto.databinding.ActivityDetailBinding
import com.alwan.thelorryresto.presentation.detail.adapter.CommentAdapter
import com.alwan.thelorryresto.presentation.detail.adapter.RestaurantPreviewAdapter
import com.alwan.thelorryresto.util.HorizontalMarginItemDecoration
import com.alwan.thelorryresto.util.VerticalMarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val restaurantPreviewAdapter =
        RestaurantPreviewAdapter { handleRestaurantPreviewClicked(it) }
    private val commentAdapter = CommentAdapter()
    private val detailViewModel: DetailViewModel by viewModels()
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.ivSend.setOnClickListener { }
        binding.llNavigate.setOnClickListener { }

        val restaurantId = intent.getIntExtra(EXTRA_ID, 0)

        setupRv()
        setupVm(restaurantId)

        binding.llMoreComment.setOnClickListener {
            loadComment(restaurantId, ++page)
        }
        binding.ivSend.setOnClickListener {
            val message = binding.etComment.text.toString()
            val sendCommentRequest = SendCommentRequest(
                restaurantId = restaurantId,
                message = message
            )

            if (message.isNotEmpty() && message.length >= 10 && message.length <= 80)
                sendComment(sendCommentRequest)
            else
                showToast("Message cannot be empty and should consist of 10-80 characters")
        }
    }

    private fun setupRv() {
        binding.rvPreviewRestaurant.apply {
            layoutManager =
                LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = restaurantPreviewAdapter
            addItemDecoration(
                HorizontalMarginItemDecoration(
                    width = resources.getDimension(R.dimen.spacing_8dp).roundToInt(),
                )
            )
            hasFixedSize()
        }

        binding.rvComment.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = commentAdapter
            addItemDecoration(
                VerticalMarginItemDecoration(
                    height = resources.getDimension(R.dimen.spacing_8dp).roundToInt(),
                    width = resources.getDimension(R.dimen.spacing_16dp).roundToInt(),
                )
            )
        }
    }

    private fun setupVm(restaurantId: Int) {

        detailViewModel.getRestaurantDetailsByID(restaurantId).observe(this) { result ->
            result.onLoading {
                showDetailLoading()
                hideDetailError()
            }.onSuccess {
                hideDetailLoading()

                if (it != null)
                    populateDetail(it)
                else
                    showDetailError(getString(R.string.empty_data))
            }.onError { message, _ ->
                hideDetailLoading()
                showDetailError(message)
                showToast(message)
            }
        }

        detailViewModel.comments.observe(this) {
            commentAdapter.submitList(it)
        }

        loadComment(restaurantId, page)
    }

    private fun populateDetail(detail: FoodDetail) = binding.apply {
        val addressName = detail.address.fullName
        val lat = detail.address.lat.toDouble()
        val lng = detail.address.lng.toDouble()

        tvAddress.text = addressName
        tvDescription.text = detail.description
        tvTitle.text = detail.title
        tvRating.text = getString(R.string.rating_value, detail.rating)
        restaurantPreviewAdapter.submitList(detail.images)

        binding.ivCopy.setOnClickListener {
            addToClipboard(addressName)
            showToast("Added to clipboard")
        }
        binding.llNavigate.setOnClickListener {
            openGoogleMaps(lat, lng)
        }
    }

    private fun loadComment(restaurantId: Int, page: Int) {
        detailViewModel.getCommentById(restaurantId, page).observe(this) { result ->
            result.onLoading {
                showCommentLoading()
                hideCommentError()
            }.onSuccess {
                hideCommentLoading()

                if (!it.isNullOrEmpty())
                    detailViewModel.addAllComments(it)
                else
                    hideCommentMore()
            }.onError { message, _ ->
                hideCommentLoading()
                showCommentError(message)
                showToast(message)
            }
        }
    }

    private fun sendComment(sendCommentRequest: SendCommentRequest) {
        detailViewModel.sendCommentToRestaurantByID(sendCommentRequest).observe(this) { result ->
            result.onLoading {
                showLoadingSendComment()
            }.onSuccess {
                hideLoadingSendComment()
                showToast(it)
            }.onError { message, _ ->
                hideLoadingSendComment()
                showToast(message)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun handleRestaurantPreviewClicked(restaurantPreview: String) {
        showToast(restaurantPreview)
    }

    private fun openGoogleMaps(latitude: Double, longitude: Double) {
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(packageManager) != null)
            startActivity(mapIntent)
        else
            showToast("Google maps is not installed")
    }

    private fun showDetailLoading() = binding.apply {
        pbDetail.show()
        clDetail.hide()
    }

    private fun hideDetailLoading() = binding.apply {
        pbDetail.hide()
        clDetail.show()
    }

    private fun showDetailError(message: String? = null) = binding.apply {
        tvDetailError.text = message ?: getString(R.string.error)
        tvDetailError.show()
        clDetail.hide()
    }

    private fun hideDetailError() = binding.apply {
        tvDetailError.hide()
    }

    private fun showCommentLoading() = binding.apply {
        pbComment.show()
        rvComment.show()
        llMoreComment.hide()
    }

    private fun hideCommentLoading() = binding.apply {
        pbComment.hide()
        llMoreComment.show()
    }

    private fun showCommentError(message: String? = null) = binding.apply {
        tvCommentError.text = message ?: getString(R.string.error)
        tvCommentError.show()
        rvComment.hide()
        llMoreComment.hide()
    }

    private fun hideCommentError() = binding.apply {
        tvCommentError.hide()
    }

    private fun hideCommentMore() = binding.apply {
        llMoreComment.hide()
    }

    private fun showLoadingSendComment() = binding.apply {
        pbSendComment.show()
        ivSend.hide()
    }

    private fun hideLoadingSendComment() = binding.apply {
        pbSendComment.hide()
        ivSend.show()
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}