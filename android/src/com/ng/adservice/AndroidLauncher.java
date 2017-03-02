package com.ng.adservice;

import android.os.Bundle;

import android.view.View;
import android.widget.RelativeLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler{

	private AdMob adMob;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView=initializeForView(new Main(this), config);
		RelativeLayout.LayoutParams gameViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		gameViewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		gameViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

		gameView.setLayoutParams(gameViewParams);
		layout.addView(gameView);

		adMob=new AdMob(this);
		adMob.embedView(layout);

		setContentView(layout);
	}


	@Override
	protected void onResume() {
		super.onResume();
		adMob.resume();
	}

	@Override
	protected void onPause() {

		adMob.pause();
		super.onPause();
	}

	@Override
	public void onDestroy() {
		adMob.destroy();
		super.onDestroy();
	}

	@Override
	public void showBannerAds(boolean isTop,boolean isBottom) {
         adMob.showAd(isTop,isBottom);
	}

	@Override
	public void showOrLoadInterstital() {
		adMob.showOrLoadInterstital();
	}
}
