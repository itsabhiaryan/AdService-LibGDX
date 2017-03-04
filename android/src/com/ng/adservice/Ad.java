package com.ng.adservice;

import android.widget.RelativeLayout;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 3/3/2017.
 */
public interface Ad {

    void showAd(boolean isTop,boolean isBottom);
    void embedView(RelativeLayout layout);

    void showOrLoadInterstitial();
    boolean showVideoAd(boolean isReward);

    void destroy();
    void start();
    void stop();
    void pause();

    void resume();

}
