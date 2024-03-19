package com.example.notebookapp

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.notebookapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcvLogin) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(listener)
        setupBottomBar()
        val menu = binding.bottomAppBar.menu
        val item1 = menu.findItem(R.id.menuHome)
        val item2 = menu.findItem(R.id.menuFinished)
        val item4 = menu.findItem(R.id.menuSearch)
        val item5 = menu.findItem(R.id.menuSettings)

        item1.icon = AppCompatResources.getDrawable(this, R.drawable.ic_home)
        item1.title = "Home"

        item2.icon = AppCompatResources.getDrawable(this, R.drawable.ic_finish)
        item2.title = "Finished"

        item4.icon = AppCompatResources.getDrawable(this, R.drawable.ic_search)
        item4.title = "Search"

        item5.icon = AppCompatResources.getDrawable(this, R.drawable.ic_setting)
        item5.title = "Settings"
    }
    private fun setupBottomBar(){
        binding.bottomAppBar.setupWithNavController(navController)
        binding.bottomAppBar.setOnItemSelectedListener(navItemSelectedListener)
        binding.btnCreate.setOnClickListener {
            if (navController.currentDestination?.id != R.id.createFragment) {
                navController.navigate(R.id.createFragment)
            }
        }
    }

    private val navItemSelectedListener = NavigationBarView.OnItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.menuHome -> {
                if (navController.currentDestination?.id != R.id.homeFragment) {
                    navController.navigate(R.id.homeFragment)
                }
                true
            }
            R.id.menuFinished -> {
                if (navController.currentDestination?.id != R.id.finishedFragment) {
                    navController.navigate(R.id.finishedFragment)
                }
                true
            }
            R.id.menuSearch -> {
                if (navController.currentDestination?.id != R.id.searchFragment) {
                    navController.navigate(R.id.searchFragment)
                }
                true
            }
            R.id.menuSettings -> {
                if (navController.currentDestination?.id != R.id.settingsFragment) {
                    navController.navigate(R.id.settingsFragment)
                }
                true
            }

            else -> {
                false
            }
        }
    }

    private val listener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.homeFragment -> {
                    showBottomMenu()
                }
                R.id.finishedFragment -> {
                    showBottomMenu()
                }
                R.id.loginFragment -> {
                    hideBottomMenu()
                }
                R.id.settingsFragment -> {
                    showBottomMenu()
                }
                R.id.createFragment -> {
                    hideBottomMenu()
                }
            }
        }



    /**
     * Hide bottom menu
     * */
    private fun hideBottomMenu() {
        lifecycleScope.launch {
            delay(230)
            binding.bottomAppBar.visibility = View.GONE
            binding.btnCreate.visibility = View.GONE
        }
    }

    /**
     * Show bottom menu
     * */
    private fun showBottomMenu() {
        lifecycleScope.launch {
            delay(200)
            binding.bottomAppBar.visibility = View.VISIBLE
            binding.btnCreate.visibility = View.VISIBLE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuHome -> {
                navController.navigate(R.id.homeFragment)
                return true
            }
            R.id.menuFinished -> {
                navController.navigate(R.id.action_homeFragment_to_finishedFragment)
                return true
            }
            R.id.menuSearch -> {
                // Handle item 2 click
                return true
            }
            R.id.menuSettings -> {
                navController.navigate(R.id.settingsFragment)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}