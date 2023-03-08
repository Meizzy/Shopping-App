package com.brujiyaseer.shoppingapp.presentation.destinations.details

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.helper.widget.Carousel
import com.brujiyaseer.shoppingapp.core.base.BaseFragment
import com.brujiyaseer.shoppingapp.databinding.FragmentDetailsBinding


class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.carousel.setAdapter(object : Carousel.Adapter {
//            override fun count(): Int = 3
//
//            override fun populate(view: View, index: Int) {
//                // need to implement this to populate the view at the given index
//            }
//
//            override fun onNewItem(index: Int) {
//                // called when an item is set
//            }
//        })


    }

}