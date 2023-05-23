package com.example.example01.publiadmob

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.example.example01.R
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import javax.inject.Inject

class AdMobInterstitial @Inject constructor(
    private val context: Context
) {
    var mInterstitialAd: InterstitialAd? = null
    fun loadAd() {
        val adUnitId = context.getString(R.string.interstitial_ad_unit_id)
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(context, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(ad: InterstitialAd) {
                mInterstitialAd = ad
                Toast.makeText(context, "add loaded", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun showAdd(activity: Activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    mInterstitialAd = null
                    loadAd()
                    Toast.makeText(context, "dismissed", Toast.LENGTH_SHORT).show()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    mInterstitialAd = null
                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    Toast.makeText(context, "showed", Toast.LENGTH_SHORT).show()
                }
            }
            mInterstitialAd?.show(activity)
        } else {
            loadAd()
        }
    }
}