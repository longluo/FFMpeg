package com.longluo.ffmpeg;

import android.app.Application;

public class FFMpegApplication extends Application {
	private static final String TAG = "FFMpegApplication";

	@Override
	public void onCreate() {

		super.onCreate();
	}

	@Override
	public void onLowMemory() {

		super.onLowMemory();
	}

	@Override
	public void onTrimMemory(int level) {

		super.onTrimMemory(level);
	}
}
