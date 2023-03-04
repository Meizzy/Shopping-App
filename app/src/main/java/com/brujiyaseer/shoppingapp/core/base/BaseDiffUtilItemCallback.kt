package com.brujiyaseer.shoppingapp.core.base

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.equals(newItem)
    }
}
