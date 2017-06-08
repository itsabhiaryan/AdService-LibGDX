package com.its.adservice;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;

/**
 * (c) 2017 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 08/06/17.
 */
public class MoPubHelper implements Ad {

    private static final String AD_UNIT_ID="XXXXXXXXX";
    private static final String INTERSTITIAL_AD_UNIT_ID= "XXXXXXXXXX";
    private MoPubView moPubView;
    public MoPubInterstitial mInterstitial;
    AndroidLauncher androidLauncher;

    public MoPubHelper(AndroidLauncher androidLauncher){
        this.androidLauncher=androidLauncher;

        moPubView = new MoPubView(androidLauncher);
        moPubView.setAdUnitId(AD_UNIT_ID);
        moPubView.loadAd();
        moPubView.setBannerAdListener(new MoPubView.BannerAdListener(){

            @Override
            public void onBannerLoaded(MoPubView banner) {

            }

            @Override
            public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {

            }

            @Override
            public void onBannerClicked(MoPubView banner) {

            }

            @Override
            public void onBannerExpanded(MoPubView banner) {

            }

            @Override
            public void onBannerCollapsed(MoPubView banner) {

            }
        });

        mInterstitial = new MoPubInterstitial(androidLauncher, INTERSTITIAL_AD_UNIT_ID);
        mInterstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener(){

            @Override
            public void onInterstitialLoaded(MoPubInterstitial interstitial) {

            }

            @Override
            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

            }

            @Override
            public void onInterstitialShown(MoPubInterstitial interstitial) {

            }

            @Override
            public void onInterstitialClicked(MoPubInterstitial interstitial) {

            }

            @Override
            public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                    mInterstitial.load();
            }
        });

        mInterstitial.load();
    }


    @Override
    public void showAd(boolean isTop, boolean isBottom) {

    }

    @Override
    public void embedView(RelativeLayout layout) {

        RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        topParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        topParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        layout.addView(moPubView, topParams);
         moPubView.setBackgroundColor(Color.BLACK);
    }

    @Override
    public void showOrLoadInterstitial() {

        androidLauncher.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mInterstitial.isReady()) {
                    mInterstitial.show();
                } else {
                   mInterstitial.load();
                }
            }
        });

    }

    @Override
    public boolean showVideoAd(boolean isReward) {
        return false;
    }

    @Override
    public void destroy() {
        moPubView.destroy();
        mInterstitial.destroy();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
