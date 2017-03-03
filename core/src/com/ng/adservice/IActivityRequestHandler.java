package com.ng.adservice;

/**
 * (c) 2010 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 03-03-2017.
 */
public interface IActivityRequestHandler {

    void showBannerAds(boolean isTop,boolean isBottom);
    void showOrLoadInterstitial();
    boolean showVideoAd(boolean isRewarded);

}
