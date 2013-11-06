package com.longluo.ffmpeg;

import java.io.IOException;

import com.media.ffmpeg.FFMpeg;
import com.media.ffmpeg.FFMpegException;
import com.media.ffmpeg.android.FFMpegMovieViewAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.longluo.ffmpeg.utils.*;

public class FFMpegPlayerActivity extends Activity {
	private static final String TAG = "FFMpegPlayerActivity";
	// private static final String LICENSE =
	// "This software uses libraries from the FFmpeg project under the LGPLv2.1";

	private FFMpegMovieViewAndroid mMovieView;

	// private WakeLock mWakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MyLog.d(TAG, "onCreate");

		super.onCreate(savedInstanceState);

		Intent it = getIntent();
		String filePath = it.getStringExtra(getResources().getString(
				R.string.input_file));
		if (filePath == null) {
			MyLog.d(TAG, "Not specified video file");
			finish();
		} else {
			try {
				FFMpeg ffmpeg = new FFMpeg();
				mMovieView = ffmpeg.getMovieView(this);
				try {
					mMovieView.setVideoPath(filePath);
				} catch (IllegalArgumentException e) {
					MyLog.e(TAG, "Can't set video: " + e.getMessage());
					FFMpegMessageBox.show(this, e);
				} catch (IllegalStateException e) {
					MyLog.e(TAG, "Can't set video: " + e.getMessage());
					FFMpegMessageBox.show(this, e);
				} catch (IOException e) {
					MyLog.e(TAG, "Can't set video: " + e.getMessage());
					FFMpegMessageBox.show(this, e);
				}
				setContentView(mMovieView);
			} catch (FFMpegException e) {
				MyLog.d(TAG, "Error when inicializing ffmpeg: " + e.getMessage());
				FFMpegMessageBox.show(this, e);
				finish();
			}
		}
	}
}
