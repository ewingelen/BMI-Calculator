package com.ewingelen.bmicalculator.core.presentation

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.ewingelen.bmicalculator.R
import com.ewingelen.bmicalculator.databinding.ActivityMainBinding
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.toolbar.apply {
            binding.toolbar.title =
                if (navController.graph.startDestinationId == navController.currentDestination?.id)
                    getString(R.string.title_add_bmi_details)
                else
                    getString(R.string.title_bmi_details)
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun getTheme(): Resources.Theme {
        val theme = super.getTheme()
        theme.applyStyle(R.style.Theme_BMICalculator, true)
        return theme
    }
}