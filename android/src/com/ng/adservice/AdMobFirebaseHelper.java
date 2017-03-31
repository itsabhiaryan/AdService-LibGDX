package com.ng.adservice;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * (c) 2017 Abhishek Aryan
 *
 * @author Abhishek Aryanø
 * @since 01/04/17.
 */

public class AdMobFirebaseHelper extends AdMobHelper implements RewardedVideoAdListener {

    private static final String APP_ID="ca-app-pub-3940256099942544~3347511713";
    private RewardedVideoAd mAd;

    AdMobFirebaseHelper(Activity activity) {

        super(activity,false);
        MobileAds.initialize(activity.getApplicationContext(), APP_ID);

        initView();

        mAd = MobileAds.getRewardedVideoAdInstance(activity);
        mAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

    }

    private void loadRewardedVideoAd() {
        mAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
    }


    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(activity, "onRewarded! currency: " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(activity, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(activity, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(activity, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(activity, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(activity, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(activity, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    private boolean isRewardShown;

    @Override
    public boolean showVideoAd(boolean isReward){

        isRewardShown=false;
        if(isReward) {

            activity.runOnUiThread(new Runnable() {
                public void run() {

                    if (mAd.isLoaded()) {
                        mAd.show();
                        isRewardShown=true;

                    } else {
                        loadRewardedVideoAd();
                    }
                }

            });
        }

        return isRewardShown;
    }

    @Override
    public void resume() {
        mAd.resume(activity);
        super.resume();
    }

    public void pause(){
        mAd.pause(activity);
        super.pause();
    }

    public void destory(){
        mAd.destroy(activity);
        super.destroy();
    }
}
