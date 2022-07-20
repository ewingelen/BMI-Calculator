package com.ewingelen.bmicalculator.result.presentation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ewingelen.bmicalculator.BuildConfig
import com.ewingelen.bmicalculator.core.presentation.BaseFragment
import com.ewingelen.bmicalculator.databinding.FragmentResultBinding
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    @Inject
    lateinit var adRequest: AdRequest

    @Inject
    lateinit var adLoader: AdLoader.Builder

    private val args: ResultFragmentArgs by navArgs()

    private companion object {
        const val GOOGLE_PLAY_LINK = "https://play.google.com/store/apps/"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: ResultViewModel by viewModels()

        val resultNumber = SpannableString(args.bmi.toString())
        resultNumber.setSpan(RelativeSizeSpan(2.5f), 0, 2, 0)

        loadAd()

        binding.apply {
            tvBmi.text = resultNumber
            tvResult.append(" ${args.name.uppercase()}, ${viewModel.result(args.bmi)}")
            tvPonderalIndexResult.append(" ${args.ponderalIndex}")

            btnRate.setOnClickListener {
                val googlePlayIntent = Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_LINK))
                startActivity(googlePlayIntent)
            }

            btnShare.setOnClickListener {
                val screenshot = makeScreenshot(binding.llResult)
                viewModel.saveBitmap(screenshot)
                shareImage(viewModel.loadBitmap())
            }
        }
    }

    private fun makeScreenshot(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun shareImage(file: File) {
        val uri = FileProvider.getUriForFile(
            requireActivity(), "${BuildConfig.APPLICATION_ID}.fileprovider", file
        )
        val intent = Intent(Intent.ACTION_SEND)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "image/*";
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(intent)
    }

    private fun loadAd() {
        adLoader
            .forNativeAd { nativeAd ->
                if (requireActivity().isDestroyed) {
                    nativeAd.destroy()
                    return@forNativeAd
                }
                displayNativeAd(binding.nativeAdView.nativeAdView, nativeAd)
            }
            .build()
            .loadAd(adRequest)
    }

    private fun displayNativeAd(adView: NativeAdView, ad: NativeAd) {
        binding.nativeAdView.apply {
            adIcon.setImageDrawable(ad.icon?.drawable)
            adHeadline.text = ad.headline
            adBody.text = ad.body
            if (ad.advertiser != null) adAdvertiser.text = ad.advertiser

            adView.iconView = adIcon
            adView.headlineView = adHeadline
            adView.bodyView = adBody
            adView.advertiserView = adAdvertiser
            adView.callToActionView = btnAction
        }

        adView.setNativeAd(ad)
        adView.visibility = View.VISIBLE
    }
}