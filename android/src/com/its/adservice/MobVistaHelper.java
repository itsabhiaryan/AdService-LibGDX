package com.its.adservice;

import android.util.Log;
import android.widget.RelativeLayout;

import com.mobvista.msdk.MobVistaConstans;
import com.mobvista.msdk.MobVistaSDK;
import com.mobvista.msdk.out.InterstitialListener;
import com.mobvista.msdk.out.MVInterstitialHandler;
import com.mobvista.msdk.out.MVRewardVideoHandler;
import com.mobvista.msdk.out.MobVistaSDKFactory;
import com.mobvista.msdk.out.RewardVideoListener;

import java.util.HashMap;
import java.util.Map;

/**
 * (c) 2017 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30/05/17.
 */
public class MobVistaHelper implements Ad {

    private HashMap<String, Object> hashMap = new HashMap<String, Object>();
    private MVInterstitialHandler mInterstitialHandler;
    private MVRewardVideoHandler mvRewardVideoHandler;
    private boolean isMobVistaInterstitialLoaded,isMobVistaRewardVideoLoaded;

    private final String APP_ID="xxxxx";
    private final String API_KEY ="xxxxxxx";
    private final String INTERSTITIAL_UNIT_ID ="xxxxxx";
    private final String VIDEO_UNIT_ID ="xxxxx";
    private final String VIDEO_SHOW_ID="xxxxx";

    MobVistaSDK sdk;

    public MobVistaHelper(AndroidLauncher activity){

        sdk = MobVistaSDKFactory.getMobVistaSDK();
        Map<String,String> Map = sdk.getMVConfigurationMap(APP_ID,API_KEY);
        sdk.init(Map, activity);

        //MobvistaAd mMobvistaAd = new MobvistaAd(this, "your_app_ID", "your_apiKey");

        hashMap.put(MobVistaConstans.PROPERTIES_UNIT_ID, INTERSTITIAL_UNIT_ID);

        mInterstitialHandler=new MVInterstitialHandler(activity,hashMap);

        mInterstitialHandler.setInterstitialListener(new InterstitialListener() {

                @Override
                public void onInterstitialShowSuccess() {
                    isMobVistaInterstitialLoaded=true;
                    Log.e("tag", "mobvista onInterstitialShowSuccess");
                }

                @Override
                public void onInterstitialShowFail(String errorMsg) {
                    Log.e("tag", "mobvista onInterstitialShowFail errorMsg:"+errorMsg);
                }

                @Override
                public void onInterstitialLoadSuccess() {
                    Log.e("TAG", " mobvista onInterstitialLoadSuccess");
                    isMobVistaInterstitialLoaded=true;
                }

                @Override
                public void onInterstitialLoadFail(String errorMsg) {
                    Log.e("TAG", "onInterstitialLoadFail errorMsg:"+errorMsg);
                }

                @Override
                public void onInterstitialClosed() {
                    Log.e("TAG", "onInterstitialClosed");
                    isMobVistaInterstitialLoaded=false;
                    mInterstitialHandler.preload();
                }

                @Override
                public void onInterstitialAdClick() {
                    Log.e("TAG", "mobvista onInterstitialAdClick");
                }
        });

        mvRewardVideoHandler=new MVRewardVideoHandler(activity, VIDEO_UNIT_ID);

        mvRewardVideoHandler.setRewardVideoListener(new RewardVideoListener() {
                @Override
                public void onVideoLoadSuccess() {

                    isMobVistaRewardVideoLoaded=true;
                    Log.e("TAG", "mobvista onVideoLoadSuccess");
                }

                @Override
                public void onVideoLoadFail() {
                    //Log.e("TAG", "onVideoLoadFail");
                }

                @Override
                public void onShowFail(String errorMsg) {
                    //Log.e("TAG", "onShowFail=" + errorMsg);
                }

                @Override
                public void onAdShow() {
                    //Log.e("TAG", "onAdShow");
                }

                @Override
                public void onAdClose(boolean isCompleteView, String RewardName,float RewardAmout) {
                    Log.e("TAG", "reward info CLOSED:" + "RewardName:" + RewardName	+ "RewardAmout:" + RewardAmout);
                    isMobVistaRewardVideoLoaded=false;
                    mvRewardVideoHandler.load();;
                }

                @Override
                public void onVideoAdClicked(String unitId) {
                    Log.e("TAG", "onVideoAdClicked");
                }
        });

        MobvistaPreload();

    }

    public void MobvistaPreload() {
        mInterstitialHandler.preload();
        mvRewardVideoHandler.load();
    }

    @Override
    public void showAd(boolean isTop, boolean isBottom) {

    }

    @Override
    public void embedView(RelativeLayout layout) {

    }

    @Override
    public void showOrLoadInterstitial() {
        if(isMobVistaInterstitialLoaded)mInterstitialHandler.show();
    }

    @Override
    public boolean showVideoAd(boolean isReward) {

        if(isMobVistaRewardVideoLoaded) mvRewardVideoHandler.show(VIDEO_SHOW_ID);

        return isMobVistaRewardVideoLoaded;
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
}
