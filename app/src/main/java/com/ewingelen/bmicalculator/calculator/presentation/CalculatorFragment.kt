package com.ewingelen.bmicalculator.calculator.presentation

import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ewingelen.bmicalculator.R
import com.ewingelen.bmicalculator.core.presentation.BaseFragment
import com.ewingelen.bmicalculator.databinding.FragmentCalculatorBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CalculatorFragment :
    BaseFragment<FragmentCalculatorBinding>(FragmentCalculatorBinding::inflate) {

    @Inject
    lateinit var adRequest: AdRequest
    private var mInterstitialAd: InterstitialAd? = null
    private val viewModel: CalculatorViewModel by viewModels()

    private companion object {
        const val MIN_WEIGHT = 2
        const val MAX_WEIGHT = 300
        const val DEFAULT_WEIGHT = 60
        const val MIN_HEIGHT = 30
        const val MAX_HEIGHT = 220
        const val DEFAULT_HEIGHT = 160
        const val MALE = 0
        const val FEMALE = 1
        const val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val genders = arrayOf(
            getString(R.string.gender_picker_item_male),
            getString(R.string.gender_picker_item_female)
        )

        binding.apply {
            npWeight.setup(MIN_WEIGHT, MAX_WEIGHT, DEFAULT_WEIGHT)
            npHeight.setup(MIN_HEIGHT, MAX_HEIGHT, DEFAULT_HEIGHT)
            npGender.setup(MALE, FEMALE, FEMALE, genders)

            btnCalculate.setOnClickListener {
                val bmi = viewModel.calculateBmi(npWeight.value, npHeight.value)
                val ponderalIndex = viewModel.calculatePonderalIndex(npWeight.value, npHeight.value)
                showAd(
                    onAdDismissed = {
                        navigateToResultFragment(bmi, ponderalIndex, etName.text.toString())
                    }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadAd()
    }

    private fun loadAd() {
        InterstitialAd.load(requireContext(), AD_UNIT_ID, adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }

    private fun showAd(onAdDismissed: () -> Unit) {
        mInterstitialAd?.show(requireActivity())

        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                onAdDismissed()
            }
        }
    }

    private fun navigateToResultFragment(bmi: Float, ponderalIndex: Float, name: String) {
        val action = CalculatorFragmentDirections.actionCalculatorFragmentToResultFragment(
            bmi, ponderalIndex, name
        )
        findNavController().navigate(action)
    }

    private fun NumberPicker.setup(
        minValue: Int, maxValue: Int, defaultValue: Int, displayedValues: Array<String> = arrayOf()
    ) {
        this.minValue = minValue
        this.maxValue = maxValue
        this.value = defaultValue
        if (displayedValues.isNotEmpty()) this.displayedValues = displayedValues
    }
}

