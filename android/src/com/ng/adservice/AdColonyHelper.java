package com.ng.adservice;

import android.app.Activity;
import android.graphics.Color;
import android.widget.RelativeLayout;
import com.adcolony.sdk.*;
import com.badlogic.gdx.ApplicationListener;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 3/4/2017.
 */
public class AdColonyHelper implements Ad {

    public static final String APP_ID="XXXXXXXXXXXXXXXXX";
    public static final String ZONE_IDS="XXXXXXXXXXXXXXXXX";
    private Activity activity;

    private AdColonyInterstitial ad;
    private boolean hasInterstitial;

    public AdColonyHelper(Activity activity){

        this.activity=activity;


        AdColony.configure(activity, APP_ID, ZONE_IDS);
        //loadNativeAd();


        AdColonyAppOptions appOptions = new AdColonyAppOptions().setUserID("userid");
        AdColony.configure(activity, appOptions,APP_ID, ZONE_IDS);

    }

    public void loadNativeAd(){

        AdColonyNativeAdViewListener listener = new AdColonyNativeAdViewListener()
        {
            @Override
            public void onRequestFilled( AdColonyNativeAdView ad )
            {

                /** Store this ad view object to be added to your layout when appropriate */
            }
        };

        AdColony.requestNativeAdView( ZONE_IDS, listener, AdColonyAdSize.MEDIUM_RECTANGLE );
    }


    public void changeUser(String user){
        AdColonyAppOptions appOptions = AdColony.getAppOptions().setUserID(user);
        AdColony.setAppOptions(appOptions);
    }

    @Override
    public void showAd(boolean isTop, boolean isBottom) {

    }

    @Override
    public void embedView(RelativeLayout layout) {

    }

    public void loadInterstitial(){
        AdColony.requestInterstitial(ZONE_IDS, new MyInterstitialListener(this));
    }


    @Override
    public void showOrLoadInterstitial() {

        if(hasInterstitial)
           ad.show();
        else
            loadInterstitial();
    }

    public void loadRewardVideo(){

        AdColonyAdOptions options = new AdColonyAdOptions().enableConfirmationDialog(true).enableResultsDialog(true);

        AdColonyRewardListener listener = new AdColonyRewardListener() {
            @Override
            public void onReward(AdColonyReward reward) {
                /** Query the reward object for information here */
            }
        };


        AdColony.setRewardListener(listener);
    }

    @Override
    public boolean showVideoAd(boolean isReward) {
        return false;
    }

    @Override
    public void destroy() {
        //ad.destroy();
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

    class MyInterstitialListener extends AdColonyInterstitialListener{

        AdColonyHelper adHelper;

        public MyInterstitialListener(AdColonyHelper helper){
            this.adHelper =helper;
        }

        @Override
        public void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {

            this.adHelper.ad=adColonyInterstitial;
            this.adHelper.hasInterstitial=true;
        }
        public void onRequestNotFilled(AdColonyZone zone) {
            this.adHelper.hasInterstitial=false;
        }

        public void onOpened(AdColonyInterstitial ad) {
            this.adHelper.hasInterstitial=false;
        }

        public void onClosed(AdColonyInterstitial ad) {
            this.adHelper.hasInterstitial=false;
        }

        public void onIAPEvent(AdColonyInterstitial ad, String product_id, int engagement_type) {
            this.adHelper.hasInterstitial=false;
        }

        public void onExpiring(AdColonyInterstitial ad) {
            this.adHelper.hasInterstitial=false;
        }

        public void onLeftApplication(AdColonyInterstitial ad) {
            this.adHelper.hasInterstitial=false;
        }

        public void onClicked(AdColonyInterstitial ad) {
            this.adHelper.hasInterstitial=false;
        }
    }

}
