package com.brujiyaseer.shoppingapp.presentation.adapters

import com.brujiyaseer.shoppingapp.core.base.BaseDiffUtilItemCallback
import com.brujiyaseer.shoppingapp.core.base.ListItem
import com.brujiyaseer.shoppingapp.presentation.MainScreenDelegates
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainScreenAdapter(onClick: () -> Unit) :
    AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager.addDelegate(MainScreenDelegates.goodsSectionDelegate(onClick))
    }
}