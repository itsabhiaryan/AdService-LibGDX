package com.ng.adservice;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * (c) 2010 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 02-03-2017.
 */
public class AdMob {

    private Activity activity;
    private AdView topView,bottomView;
    private String TopAdUnitId="ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx";
    private String BottomAdUnitId="ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx";



    private final int SHOW_TOP_ADS = 0;
    private final int SHOW_BOTTOM_ADS = 1;
    private final int SHOW_TOP_BOTTOM_ADS = 2;
    private final int SHOW_NONE_ADS = 3;

    protected Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case SHOW_TOP_ADS:
                {
                    topView.setVisibility(View.VISIBLE);
                    bottomView.setVisibility(View.GONE);
                    break;
                }
                case SHOW_BOTTOM_ADS:
                {
                    topView.setVisibility(View.GONE);
                    bottomView.setVisibility(View.VISIBLE);
                    break;
                }
                case SHOW_TOP_BOTTOM_ADS:
                {
                    topView.setVisibility(View.VISIBLE);
                    bottomView.setVisibility(View.VISIBLE);
                    break;
                }
                case SHOW_NONE_ADS:
                {
                    topView.setVisibility(View.GONE);
                    bottomView.setVisibility(View.GONE);
                    break;
                }
            }
        }
    };

    public AdMob(Activity activity){
        this.activity=activity;

        // Create and setup the AdMob view
        topView = new AdView(activity);
        topView.setAdSize(AdSize.BANNER);
        topView.setAdUnitId(TopAdUnitId);

        bottomView = new AdView(activity);
        bottomView.setAdSize(AdSize.BANNER);
        bottomView.setAdUnitId(BottomAdUnitId);

        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        topView.loadAd(adRequestBuilder.build());
        bottomView.loadAd(adRequestBuilder.build());

        // Do the stuff that initialize() would do for you
        callForInitializeForView();
    }


    public void callForInitializeForView(){

        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }

    public void embedView(RelativeLayout layout){

        // Add the AdMob view
        RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        topParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        topParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layout.addView(topView, topParams);


        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layout.addView(bottomView, bottomParams);

    }

    public void resume(){
        topView.resume();
        bottomView.resume();
    }

    public void pause(){
        topView.pause();
        bottomView.pause();
    }

    public void destroy(){
        topView.destroy();
        bottomView.destroy();
    }

    public void showAd(boolean isTop,boolean isBottom){

        if(isBottom && isTop)
        handler.sendEmptyMessage(SHOW_TOP_BOTTOM_ADS);
        else if(!isBottom && !isTop)
        handler.sendEmptyMessage(SHOW_NONE_ADS);
        else if(isTop && !isBottom)
        handler.sendEmptyMessage(SHOW_TOP_ADS);
        else if(!isTop && isBottom)
        handler.sendEmptyMessage(SHOW_BOTTOM_ADS);
    }

}
