package com.brujiyaseer.shoppingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.brujiyaseer.shoppingapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.mobile_navigation)

        graph.setStartDestination(R.id.navigation_main)
//        if (false) {
//            graph.setStartDestination(R.id.navigation_sign_in)
//        } else {
//            graph.setStartDestination(R.id.navigation_main)
//        }

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)

    }

}