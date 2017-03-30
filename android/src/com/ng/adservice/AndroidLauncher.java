package com.ng.adservice;

import android.os.Bundle;

import android.transition.ChangeTransform;
import android.view.View;
import android.widget.RelativeLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler{

	private Ad ad;
	public RelativeLayout layout;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView=initializeForView(new Main(this), config);
		RelativeLayout.LayoutParams gameViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		gameViewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		gameViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

		gameView.setLayoutParams(gameViewParams);
		layout.addView(gameView);

		ad=new StartAppHelper(this);
		ad.embedView(layout);

		setContentView(layout);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if(ad instanceof StartAppHelper)
		((StartAppHelper)ad).saveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if(ad instanceof StartAppHelper)
		((StartAppHelper)ad).restoreInstanceState(savedInstanceState);
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		ad.resume();
	}

	@Override
	protected void onPause() {

		ad.pause();
		super.onPause();
	}

	@Override
	public void onDestroy() {
		ad.destroy();
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();
		ad.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		ad.stop();
	}

	@Override
	public void showBannerAds(boolean isTop,boolean isBottom) {
         ad.showAd(isTop,isBottom);
	}

	@Override
	public void showOrLoadInterstitial() {
		ad.showOrLoadInterstitial();
	}

	@Override
	public boolean showVideoAd(boolean isRewarded) {

		return ad.showVideoAd(isRewarded);
	}
}
