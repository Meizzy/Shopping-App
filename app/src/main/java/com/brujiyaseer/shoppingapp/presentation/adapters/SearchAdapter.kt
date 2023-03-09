package com.brujiyaseer.shoppingapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brujiyaseer.shoppingapp.databinding.ItemSearchBinding

class SearchViewHolder(val itemSearchBinding: ItemSearchBinding) :
    RecyclerView.ViewHolder(itemSearchBinding.root)

class SearchAdapter(private var words: List<String>) : RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemSearchBinding =
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(itemSearchBinding)
    }

    fun loadNewData(words: List<String>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemSearchBinding.tvSearch.text = words[position]
    }

    override fun getItemCount() = words.size
}