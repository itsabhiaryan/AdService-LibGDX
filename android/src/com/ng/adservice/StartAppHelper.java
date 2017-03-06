package com.ng.adservice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerListener;
import com.startapp.android.publish.adsCommon.AutoInterstitialPreferences;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.VideoListener;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;

/**
 * (c) 2010 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 07-03-2017.
 */
public class StartAppHelper implements Ad {

    Activity activity;
    Banner startAppBannerTop,startAppBannerBottom;
    private StartAppAd startAppAd ;
    private String App_ID="xxxxxxxxx";
    boolean hasInterstitial,hasRewardAd ;

    public StartAppHelper(Activity activity){
        this.activity=activity;
        StartAppSDK.init(activity, App_ID, true);

        StartAppAd.disableSplash();     //The Splash Ad is enabled by default.


        startAppBannerTop = new Banner(activity, new BannerListener() {
            @Override
            public void onReceiveAd(View banner) {
            }
            @Override
            public void onFailedToReceiveAd(View banner) {
            }
            @Override
            public void onClick(View banner) {
            }
        });

        startAppBannerBottom = new Banner(activity, new BannerListener() {
            @Override
            public void onReceiveAd(View banner) {
            }
            @Override
            public void onFailedToReceiveAd(View banner) {
            }
            @Override
            public void onClick(View banner) {
            }
        });


        startAppAd= new StartAppAd(activity);
    }

    /** show an Interstitial Ad each time an activity is changed. **/

    public void enableAutoInterstitial(){

        StartAppAd.enableAutoInterstitial();
        StartAppAd.setAutoInterstitialPreferences(new AutoInterstitialPreferences().setSecondsBetweenAds(60));   //time frequency(in seconds)
        StartAppAd.setAutoInterstitialPreferences(new AutoInterstitialPreferences().setActivitiesBetweenAds(3));  //activity frequency
    }

    @Override
    public void showAd(boolean isTop, boolean isBottom) {

        if(isTop && !startAppBannerTop.isShown())
            startAppBannerTop.showBanner();

        if(isBottom && !startAppBannerBottom.isShown())
            startAppBannerBottom.showBanner();

        if(!isTop && startAppBannerTop.isShown())
            startAppBannerTop.hideBanner();

        if(!isBottom && startAppBannerBottom.isShown())
            startAppBannerBottom.hideBanner();

    }

    @Override
    public void embedView(RelativeLayout layout) {

        RelativeLayout.LayoutParams bannerParametersTop = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        bannerParametersTop.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bannerParametersTop.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layout.addView(startAppBannerTop, bannerParametersTop);

        RelativeLayout.LayoutParams bannerParametersBottom = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        bannerParametersBottom.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bannerParametersBottom.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layout.addView(startAppBannerBottom, bannerParametersBottom);
    }

    @Override
    public void showOrLoadInterstitial() {

        StartAppAd.disableAutoInterstitial();
        //StartAppAd.showAd(activity);
        startAppAd.showAd();

        if(hasInterstitial){

            startAppAd.showAd(new AdDisplayListener() {

                @Override
                public void adHidden(com.startapp.android.publish.adsCommon.Ad ad) {

                }

                @Override
                public void adDisplayed(com.startapp.android.publish.adsCommon.Ad ad) {

                }

                @Override
                public void adClicked(com.startapp.android.publish.adsCommon.Ad ad) {

                }

                @Override
                public void adNotDisplayed(com.startapp.android.publish.adsCommon.Ad ad) {

                }

            });
            hasInterstitial=false;

        }else{

            startAppAd.loadAd (new AdEventListener() {
                @Override
                public void onReceiveAd(com.startapp.android.publish.adsCommon.Ad ad) {
                    hasInterstitial=true;
                }

                @Override
                public void onFailedToReceiveAd(com.startapp.android.publish.adsCommon.Ad ad) {
                    hasInterstitial=false;
                }
            });
        }


    }

    public void saveInstanceState(Bundle outState){
        startAppAd.onSaveInstanceState(outState);
    }

    public void restoreInstanceState(Bundle outState){
        startAppAd.onRestoreInstanceState(outState);
    }

    @Override
    public boolean showVideoAd(boolean isReward) {

        if(isReward)showRewardVideoAd();

        return false;
    }

    public void showRewardVideoAd(){

        if(hasRewardAd){

            startAppAd.showAd();
            hasRewardAd=false;

        }else {

            startAppAd.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
                @Override
                public void onReceiveAd(com.startapp.android.publish.adsCommon.Ad ad) {
                    hasRewardAd = true;
                }

                @Override
                public void onFailedToReceiveAd(com.startapp.android.publish.adsCommon.Ad ad) {
                    hasRewardAd = false;
                }
            });

            startAppAd.setVideoListener(new VideoListener() {
                @Override
                public void onVideoCompleted() {
                    // Grant user with the reward
                }
            });
        }
    }

    @Override
    public void destroy() {

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


    public void onBackPressed() {
        //StartAppAd.onBackPressed(activity);
        startAppAd.onBackPressed();
    }

}
