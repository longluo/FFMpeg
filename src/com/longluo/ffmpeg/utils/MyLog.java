package com.longluo.ffmpeg.utils;

import android.util.Log;

public final class MyLog {

	private static final boolean LOGALL = true;
	private static final boolean LOGV = LOGALL && true;
	private static final boolean LOGD = LOGALL && true;
	private static final boolean LOGI = LOGALL && true;
	private static final boolean LOGW = LOGALL && true;
	private static final boolean LOGE = LOGALL && true;

	public static int v(String tag, String msg) {
		if (LOGV) {
			return Log.v(tag, msg);
		} else {
			return -1;
		}
	}

	public static int v(String tag, String msg, Throwable tr) {
		if (LOGV) {
			return Log.v(tag, msg, tr);
		} else {
			return -1;
		}
	}

	public static int v(String tag, boolean debug, String msg) {
		if (debug) {
			return v(tag, msg);
		} else {
			return -1;
		}
	}

	public static int d(String tag, String msg) {
		if (LOGD) {
			return Log.d(tag, msg);
		} else {
			return -1;
		}
	}

	public static int d(String tag, String msg, Throwable tr) {
		if (LOGD) {
			return Log.d(tag, msg, tr);
		} else {
			return -1;
		}
	}

	public static int i(String tag, String msg) {
		if (LOGI) {
			return Log.i(tag, msg);
		} else {
			return -1;
		}
	}

	public static int i(String tag, String msg, Throwable tr) {
		if (LOGI) {
			return Log.i(tag, msg, tr);
		} else {
			return -1;
		}
	}

	public static int w(String tag, String msg) {
		if (LOGW) {
			return Log.w(tag, msg);
		} else {
			return -1;
		}
	}

	public static int w(String tag, String msg, Throwable tr) {
		if (LOGW) {
			return Log.w(tag, msg, tr);
		} else {
			return -1;
		}
	}

	public static int e(String tag, String msg) {
		if (LOGE) {
			return Log.e(tag, msg);
		} else {
			return -1;
		}
	}

	public static int e(String tag, String msg, Throwable tr) {
		if (LOGE) {
			return Log.e(tag, msg, tr);
		} else {
			return -1;
		}
	}
}
