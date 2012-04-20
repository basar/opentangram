package com.basarc.opentangram;

import android.content.Context;
import android.view.WindowManager;

public final class Globals {

	public final static String TAG = "OpenTangram";

	/**
	 * To prevent to create new objects from this class
	 */
	private Globals() {

	}

	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}

	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getHeight();
	}

}
