package com.its.adservice;

import android.widget.RelativeLayout;
import android.widget.Toast;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.sdk.InMobiSdk;

import java.util.Map;

/**
 * Created by Abhishek Aryan on 5/23/2017.
 */

public class InMobiHelper implements Ad {

    public static String ACCOUNT_ID="xxxxxxxxxxxx";
    public static long BANNER_UNIT_ID=111111L;
    public static long INTERSTITIAL_UNIT_ID=1111111111L;
    public static long VIDEO_UNIT_ID=11111111111L;
    public static long REWARD_VIDEO_UNIT_ID=11111111111L;

    InMobiBanner bannerAd;
    InMobiInterstitial interstitialAd, rewardVideoAd, videoAd;
    boolean isInterstitialLoaded, isVideoLoaded, isRewardLoaded;
    AndroidLauncher activity;

    public InMobiHelper(AndroidLauncher androidLauncher){

        this.activity=androidLauncher;
        InMobiSdk.init(activity,ACCOUNT_ID);
        InMobiSdk.setLogLevel(InMobiSdk.LogLevel.DEBUG);
        //InMobiSdk.setLocation(locationObj);
        //InMobiSdk.setLocationWithCityStateCountry(“city”,“state”,“country”);
        //InMobiSdk.setGender(InMobiSdk.Gender.MALE); // or InMobiSdk.Gender.FEMALE

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                createBanner(activity.layout);
                createRewardVideo();
                createInterstitialAd();
            }
        });
    }

    public void createBanner(final RelativeLayout layout){

         bannerAd = new InMobiBanner(activity, BANNER_UNIT_ID);
         bannerAd.setAnimationType(InMobiBanner.AnimationType.ROTATE_HORIZONTAL_AXIS);

         RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(640, 100);
         topParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
         topParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

         bannerAd.setListener(new InMobiBanner.BannerAdListener(){

                    @Override
                    public void onAdLoadSucceeded(InMobiBanner inMobiBanner) {

                    }

                    @Override
                    public void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus) {
                        Toast.makeText(activity, "Banner Loading Failed"+inMobiAdRequestStatus.getMessage()+inMobiAdRequestStatus.getStatusCode(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onAdDisplayed(InMobiBanner inMobiBanner) {

                    }

                    @Override
                    public void onAdDismissed(InMobiBanner inMobiBanner) {

                    }

                    @Override
                    public void onAdInteraction(InMobiBanner inMobiBanner, Map<Object, Object> map) {

                    }

                    @Override
                    public void onUserLeftApplication(InMobiBanner inMobiBanner) {

                    }

                    @Override
                    public void onAdRewardActionCompleted(InMobiBanner inMobiBanner, Map<Object, Object> map) {

                    }
                });

         layout.addView(bannerAd, topParams);
         bannerAd.load();
    }


    public void createInterstitialAd(){

        interstitialAd = new InMobiInterstitial(activity,INTERSTITIAL_UNIT_ID ,
         new InMobiInterstitial.InterstitialAdListener2() {

            @Override
            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                Toast.makeText(activity, "Interstitial Loading Failed"+inMobiAdRequestStatus.getMessage()+inMobiAdRequestStatus.getStatusCode(), Toast.LENGTH_LONG).show();
                isInterstitialLoaded =false;
            }

            @Override
            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                Toast.makeText(activity, "Interstitial Ad Received", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {

                Toast.makeText(activity, "Interstitial Loading Succeeded", Toast.LENGTH_SHORT).show();
                isInterstitialLoaded =true;
            }

            @Override
            public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

            }

            @Override
            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
                isInterstitialLoaded =false;
            }

            @Override
            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {

            }

            @Override
            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {

            }

            @Override
            public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

            }

            @Override
            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                isInterstitialLoaded =false;
                interstitialAd.load();
            }

            @Override
            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
                isInterstitialLoaded =false;
            }
        });

        loadInterstitialAd();
    }

    public void loadInterstitialAd(){
        interstitialAd.load();
    }

    public void createRewardVideo(){

        rewardVideoAd = new InMobiInterstitial(activity, REWARD_VIDEO_UNIT_ID, new InMobiInterstitial.InterstitialAdListener2() {

            @Override
            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                Toast.makeText(activity, "RewardVideo Loading Failed"+inMobiAdRequestStatus.getMessage()+inMobiAdRequestStatus.getStatusCode(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                Toast.makeText(activity, "Video onAdReceived"+inMobiInterstitial.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {

                Toast.makeText(activity, "Video Loading Succeeded"+inMobiInterstitial.toString(), Toast.LENGTH_SHORT).show();
                isRewardLoaded =true;
            }

            @Override
            public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

            }

            @Override
            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {

            }

            @Override
            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {

            }

            @Override
            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {

            }

            @Override
            public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

            }

            @Override
            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                rewardVideoAd.load();
                isRewardLoaded =false;
            }

            @Override
            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {

            }
        });

        rewardVideoAd.load();
    }

    @Override
    public void showAd(boolean isTop, boolean isBottom) {

    }

    @Override
    public void embedView(final RelativeLayout layout) {

    }

    @Override
    public void showOrLoadInterstitial() {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isInterstitialLoaded) {
                    interstitialAd.show();
                }else
                    interstitialAd.load();
            }
        });
    }

    @Override
    public boolean showVideoAd(final boolean isReward) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isReward){
                    if(isRewardLoaded)
                        rewardVideoAd.show();
                    else
                        rewardVideoAd.load();

                }else{

                    if(isVideoLoaded)
                        videoAd.show();
                    else
                        videoAd.load();
                }
            }
        });

        return false;
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
        if(bannerAd!=null) bannerAd.pause();
    }

    @Override
    public void resume() {
        if(bannerAd!=null) bannerAd.resume();
    }

   /* class MyListener implements InMobiInterstitial.InterstitialAdListener2 {

        public MyListener(){

        }

        @Override
        public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {

        }

        @Override
        public void onAdReceived(InMobiInterstitial inMobiInterstitial) {

        }

        @Override
        public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
            isInterstitialLoaded = true;
        }

        @Override
        public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

        }

        @Override
        public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {

        }

        @Override
        public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {

        }

        @Override
        public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {

        }

        @Override
        public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

        }

        @Override
        public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {

        }

        @Override
        public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {

        }
    }*/
}
