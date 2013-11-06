package com.longluo.ffmpeg;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.longluo.ffmpeg.utils.MyLog;
import com.media.ffmpeg.FFMpeg;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class FFMpegFileExplorer extends ListActivity {
	private static final String TAG = "FFMpegFileExplorer";

	private String mRoot = "/sdcard";
	private TextView mTextViewLocation;
	private File[] mFiles;

	private Context mContext;

	private AdView adView;
	private final String MY_AD_UNIT_ID = "a15279ff3f35652";
	private AdView adLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MyLog.d(TAG, "onCreate...");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ffmpeg_file_explorer);
		mContext = this;

		mTextViewLocation = (TextView) findViewById(R.id.textview_path);
		getDirectory(mRoot);
		MobclickAgent.setDebugMode(true);
		UmengUpdateAgent.update(mContext);

		adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);

		adLayout = (AdView) findViewById(R.id.adView);
		adLayout.addView(adView);
		adView.loadAd(new AdRequest());
	}

	protected static boolean checkExtension(File file) {
		String[] exts = FFMpeg.EXTENSIONS;
		for (int i = 0; i < exts.length; i++) {
			if (file.getName().indexOf(exts[i]) > 0) {
				return true;
			}
		}
		return false;
	}

	private void sortFilesByDirectory(File[] files) {
		Arrays.sort(files, new Comparator<File>() {

			public int compare(File f1, File f2) {
				return Long.valueOf(f1.length()).compareTo(f2.length());
			}

		});
	}

	private void getDirectory(String dirPath) {
		MyLog.d(TAG, "getDirectory: dirPath=" + dirPath);

		try {
			mTextViewLocation.setText("Location: " + dirPath);

			File f = new File(dirPath);
			File[] temp = f.listFiles();

			sortFilesByDirectory(temp);

			File[] files = null;
			if (!dirPath.equals(mRoot)) {
				files = new File[temp.length + 1];
				System.arraycopy(temp, 0, files, 1, temp.length);
				files[0] = new File(f.getParent());
			} else {
				files = temp;
			}

			mFiles = files;
			setListAdapter(new FileExplorerAdapter(this, files,
					temp.length == files.length));
		} catch (Exception ex) {
			FFMpegMessageBox.show(this, "Error", ex.getMessage());
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		MyLog.d(TAG, "onListItemClick:l=" + l + " v=" + v + " positio="
				+ position + " id=" + id);

		File file = mFiles[position];

		if (file.isDirectory()) {
			if (file.canRead())
				getDirectory(file.getAbsolutePath());
			else {
				FFMpegMessageBox.show(this, "Error", "[" + file.getName()
						+ "] folder can't be read!");
			}
		} else {
			if (!checkExtension(file)) {
				StringBuilder strBuilder = new StringBuilder();
				for (int i = 0; i < FFMpeg.EXTENSIONS.length; i++)
					strBuilder.append(FFMpeg.EXTENSIONS[i] + " ");
				FFMpegMessageBox.show(
						this,
						"Error",
						"File must have this extensions: "
								+ strBuilder.toString());
				return;
			}

			startPlayer(file.getAbsolutePath());
		}
	}

	private void startPlayer(String filePath) {
		MyLog.d(TAG, "startPlayer: filePath=" + filePath);

		Intent i = new Intent(this, FFMpegPlayerActivity.class);
		i.putExtra(getResources().getString(R.string.input_file), filePath);
		startActivity(i);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(mContext);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(mContext);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onStop() {
		super.onStop();

		UmengUpdateAgent.setUpdateOnlyWifi(true);
		UmengUpdateAgent.setUpdateAutoPopup(true);
		UmengUpdateAgent.setUpdateListener(null);
		UmengUpdateAgent.setDownloadListener(null);
		UmengUpdateAgent.setDialogListener(null);
	}

	@Override
	protected void onDestroy() {
		if (adView != null) {
			adView.destroy();
		}

		super.onDestroy();
	}
}
