package com.brujiyaseer.shoppingapp.presentation.destinations.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.brujiyaseer.shoppingapp.R
import com.brujiyaseer.shoppingapp.core.base.BaseFragment
import com.brujiyaseer.shoppingapp.core.utils.Resource
import com.brujiyaseer.shoppingapp.core.utils.snackBar
import com.brujiyaseer.shoppingapp.core.utils.visible
import com.brujiyaseer.shoppingapp.databinding.FragmentDetailsBinding
import com.brujiyaseer.shoppingapp.domain.model.Details
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel by viewModels<DetailsViewModel>()
    private var quantity = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_details_to_navigation_home)
            }

            viewModel.detailsLiveData.observe(viewLifecycleOwner) {
                progressBar.visible(true)
                when (it) {
                    is Resource.Failure -> {
                        progressBar.visible(false)
                        requireActivity().snackBar("error ${it.exception}")
                    }
                    Resource.Loading -> progressBar.visible(true)
                    is Resource.Success -> {
                        progressBar.visible(false)
                        loadDetails(it.value)
                        tvTotalPrice.text =
                            getString(R.string.total_price, it.value.price * quantity)

                    }
                }
            }
        }


    }

    private fun loadDetails(details: Details) {
        with(binding) {
            colorView0.setCardBackgroundColor(Color.parseColor(details.colors[0]))
            colorView1.setCardBackgroundColor(Color.parseColor(details.colors[1]))
            colorView2.setCardBackgroundColor(Color.parseColor(details.colors[2]))

            detailsFeatures.text = details.description

            lifecycleScope.launchWhenResumed {
                Glide.with(imageView0.context).load(details.image_urls[0]).into(imageView0)
                Glide.with(mainImageView.context).load(details.image_urls[1]).into(mainImageView)
                Glide.with(imageView1.context).load(details.image_urls[1]).into(imageView1)
                Glide.with(imageView2.context).load(details.image_urls[2]).into(imageView2)

//            binding.carousel.setAdapter(object : Carousel.Adapter {
//                override fun count(): Int = details.image_urls.size
//
//                override fun populate(view: View, index: Int) {
//                    // need to implement this to populate the view at the given index
//                    Glide.with(view).load(details.image_urls[index]).into(view as ImageView)
//                }
//
//                override fun onNewItem(index: Int) {
//                    // called when an item is set
//                    Glide.with(mainImageView.context).load(details.image_urls[index])
//                        .into(mainImageView)
//                }
//            })
            }

            btnMinus.setOnClickListener {

                if (quantity > 0)
                    quantity--
                tvQuantity.text = getString(R.string.quantity, quantity)
                tvTotalPrice.text = getString(R.string.total_price, details.price * quantity)
            }
            btnPlus.setOnClickListener {
                quantity++
                tvQuantity.text = getString(R.string.quantity, quantity)
                tvTotalPrice.text = getString(R.string.total_price, details.price * quantity)
            }
            tvQuantity.text = getString(R.string.quantity, quantity)
            productName.text = details.name
            tvReviews.text = getString(R.string.reviews, details.number_of_reviews)
            tvPrice.text = getString(R.string.tv_price, details.price.toString())
            tvRatings.text = details.rating.toString()
            tvTotalPrice.text = getString(R.string.total_price, details.price * quantity)
        }
    }

}