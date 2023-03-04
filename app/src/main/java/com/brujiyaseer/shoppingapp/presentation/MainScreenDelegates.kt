package com.brujiyaseer.shoppingapp.presentation

import android.util.Log
import com.brujiyaseer.shoppingapp.R
import com.brujiyaseer.shoppingapp.core.base.ListItem
import com.brujiyaseer.shoppingapp.databinding.ItemFlashSaleBinding
import com.brujiyaseer.shoppingapp.databinding.ItemGoodsSectionBinding
import com.brujiyaseer.shoppingapp.databinding.ItemLatestBinding
import com.brujiyaseer.shoppingapp.domain.model.FlashSale
import com.brujiyaseer.shoppingapp.domain.model.GoodsSectionItem
import com.brujiyaseer.shoppingapp.domain.model.Latest
import com.brujiyaseer.shoppingapp.presentation.adapters.GoodsItemAdapter
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

private const val TAG = "MainScreenDelegates"
object MainScreenDelegates {

    fun goodsSectionDelegate() =
        adapterDelegateViewBinding<GoodsSectionItem, ListItem, ItemGoodsSectionBinding>({ layoutInflater, parent ->
            ItemGoodsSectionBinding.inflate(
                layoutInflater, parent, false
            )
        }) {
            //onCreateViewHolder
            val adapter = GoodsItemAdapter()
            Log.d(TAG, "adapter called ${item.goods}")
            binding.placeRecycler.adapter = adapter
            bind {
                // onBindViewHolder
                Log.d(TAG, item.goods.toString())
                binding.tvTitle.text = getString(R.string.latest)//item.title
                adapter.items = item.goods
            }
        }

    fun latestItemDelegate() =
        adapterDelegateViewBinding<Latest, ListItem, ItemLatestBinding>({ layoutInflater, parent ->
            ItemLatestBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }) {
            bind {
                with(binding) {
                    tvName.text = item.name
                    price.text = item.price.toString()
                    category.text = item.category
                    Glide.with(context).load(item.image_url).into(imageLatest)
                }
            }
        }

    fun flashSaleItemDelegate() =
        adapterDelegateViewBinding<FlashSale, ListItem, ItemFlashSaleBinding>({ layoutInflater, parent ->
            ItemFlashSaleBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }) {
            bind {
                with(binding) {
                    tvName.text = item.name
                    price.text = item.price.toString()
                    category.text = item.category
                    tvDiscount.text = item.discount.toString()
                    Glide.with(context).load(item.image_url).into(imageFlashSale)
                }
            }

        }
}