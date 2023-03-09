package com.brujiyaseer.shoppingapp.presentation

import android.util.Log
import com.brujiyaseer.shoppingapp.R
import com.brujiyaseer.shoppingapp.core.base.ListItem
import com.brujiyaseer.shoppingapp.core.utils.onClick
import com.brujiyaseer.shoppingapp.databinding.ItemFlashSaleBinding
import com.brujiyaseer.shoppingapp.databinding.ItemGoodsSectionBinding
import com.brujiyaseer.shoppingapp.databinding.ItemLatestBinding
import com.brujiyaseer.shoppingapp.domain.model.FlashSale
import com.brujiyaseer.shoppingapp.domain.model.GoodsSectionItem
import com.brujiyaseer.shoppingapp.domain.model.Latest
import com.brujiyaseer.shoppingapp.presentation.adapters.GoodsItemAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

private const val TAG = "MainScreenDelegates"

object MainScreenDelegates {


    fun goodsSectionDelegate(onClick: () -> Unit) =
        adapterDelegateViewBinding<GoodsSectionItem, ListItem, ItemGoodsSectionBinding>({ layoutInflater, parent ->
            ItemGoodsSectionBinding.inflate(
                layoutInflater, parent, false
            )
        }) {
            //onCreateViewHolder

            bind {
                // onBindViewHolder
                val adapter = GoodsItemAdapter(onClick)
                Log.d(TAG, "adapter called ${item.goods}")
                binding.placeRecycler.adapter = adapter
                binding.tvTitle.text = item.title
                adapter.items = item.goods
            }
        }

    fun latestItemDelegate() =
        adapterDelegateViewBinding<Latest, ListItem, ItemLatestBinding>({ layoutInflater, parent ->
            ItemLatestBinding.inflate(
                layoutInflater, parent, false
            )
        }) {
            bind {
                with(binding) {
                    val radius = 16f
                    tvName.text = item.name
                    price.text = getString(R.string.tv_price, item.price.toString())
                    category.text = item.category
                    Glide.with(context).load(item.image_url).transform(
                        CenterCrop(), GranularRoundedCorners(radius, radius, radius, radius)
                    ).transition(DrawableTransitionOptions.withCrossFade()).into(imageLatest)
                }
            }
        }

    fun flashSaleItemDelegate(
        onGameClick: () -> Unit
    ) =
        adapterDelegateViewBinding<FlashSale, ListItem, ItemFlashSaleBinding>({ layoutInflater, parent ->
            ItemFlashSaleBinding.inflate(
                layoutInflater, parent, false
            )
        }) {
            bind {
                with(binding) {
                    val radius = 16f

                    tvName.text = item.name
                    price.text = getString(R.string.flash_sale_price, item.price.toString())
                    category.text = item.category
                    tvDiscount.text = getString(R.string.tv_discount, item.discount.toString())
                    Glide.with(context).load(item.image_url).transform(
                            CenterCrop(), GranularRoundedCorners(radius, radius, radius, radius)
                        ).transition(DrawableTransitionOptions.withCrossFade()).into(imageFlashSale)

                    binding.root.onClick { onGameClick.invoke() }
                }
            }

        }
}