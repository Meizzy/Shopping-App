package com.brujiyaseer.shoppingapp.presentation.destinations.search

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.brujiyaseer.shoppingapp.R
import com.brujiyaseer.shoppingapp.core.base.BaseFragment
import com.brujiyaseer.shoppingapp.databinding.FragmentSearchBinding

private const val TAG = "FragmentSearch"

class FragmentSearch : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    MenuProvider {

    private var searchView: SearchView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        val toolbar: Toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchManager = requireActivity().getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val searchableInfo = searchManager.getSearchableInfo(requireActivity().componentName)
        searchView?.setSearchableInfo(searchableInfo)

        searchView?.queryHint = requireActivity().getString(R.string.search_hint)
        searchView?.isIconified = false
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, ".onQueryTextSubmit: called")

                searchView?.clearFocus()
                if ((query != null) && query.isNotEmpty()) {
                    Log.d(TAG, "query is $query")
                }
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchView?.setOnCloseListener {
            findNavController().navigate(R.id.action_navigation_search_to_navigation_main)
            false
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            findNavController().navigate(R.id.action_navigation_search_to_navigation_main)
            Log.d(TAG, "onBackPressed")
            return true
        }
        return false
    }


}