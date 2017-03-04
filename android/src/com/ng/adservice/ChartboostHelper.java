package com.ng.adservice;

import android.app.Activity;
import android.util.Log;
import android.widget.RelativeLayout;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;

/**
 * (c) 2010 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 03-03-2017.
 */

  /* Chartboost does not support banner ads. You can use Static Interstitial and Video Interstitial Ad.*/

public class ChartboostHelper implements Ad {

    private Activity activity;
    private static final String appId="XX";
    private static final String appSignature="XXX";

    public ChartboostHelper(Activity activity){

        this.activity=activity;
        Chartboost.startWithAppId(activity, appId, appSignature);
        Chartboost.onCreate(activity);
    }


    @Override
    public void showAd(boolean isTop, boolean isBottom) {

    }

    @Override
    public void embedView(RelativeLayout layout) {

    }

    public void loadInterstitial(){
        Chartboost.cacheInterstitial(CBLocation.LOCATION_DEFAULT);
    }

    @Override
    public void showOrLoadInterstitial() {

        Log.i("Chartboost","show or Load interstitial out");

        if(Chartboost.hasInterstitial(CBLocation.LOCATION_DEFAULT)) {
            Chartboost.showInterstitial(CBLocation.LOCATION_DEFAULT);
            Log.i("Chartboost","show or Load interstitial");
        }
        else
            loadInterstitial();
    }

    public void loadVideoAd(){
        Chartboost.cacheRewardedVideo(CBLocation.LOCATION_GAMEOVER);
    }

    @Override
    public boolean showVideoAd(boolean isReward) {
        Log.i("Chartboost","show or Load video out");
        if(Chartboost.hasRewardedVideo(CBLocation.LOCATION_GAMEOVER)) {
            Chartboost.showRewardedVideo(CBLocation.LOCATION_GAMEOVER);
            Log.i("Chartboost","show or Load video");
        }
        else
            loadVideoAd();
        return false;
    }

    @Override
    public void start(){
        Chartboost.onStart(activity);
    }

    @Override
    public void stop(){
        Chartboost.onStop(activity);
    }

    @Override
    public void destroy() {
        Chartboost.onDestroy(activity);
    }

    @Override
    public void pause() {
        Chartboost.onPause(activity);
    }

    @Override
    public void resume() {
        Chartboost.onResume(activity);
    }
}
