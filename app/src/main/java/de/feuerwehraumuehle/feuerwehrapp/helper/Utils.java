package de.feuerwehraumuehle.feuerwehrapp.helper;


import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.view.Window;
import android.view.WindowManager;

import de.feuerwehraumuehle.feuerwehrapp.R;

/**
 * Created by mmi on 19.02.2017.
 */

public class Utils {

	public static void setStatusBarBlack(Activity activity) {
		Window window = activity.getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
	}

	public static void setActionBarTitle(ActionBar actionBar, String message) {
		if (actionBar != null) {
			actionBar.setTitle(message);
		}
	}

	public static void activateActionBarBackButton(ActionBar actionBar, boolean activate) {
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(activate);
		}
	}

	public static void setActionBarBackgroundColor(ActionBar actionBar, int color) {
		if (actionBar != null) {
			actionBar.setBackgroundDrawable(new ColorDrawable(color));
		}
	}
}
