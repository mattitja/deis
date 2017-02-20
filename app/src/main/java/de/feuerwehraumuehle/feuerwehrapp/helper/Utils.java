package de.feuerwehraumuehle.feuerwehrapp.helper;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import de.feuerwehraumuehle.feuerwehrapp.R;

/**
 * Created by mmi on 19.02.2017.
 */

public class Utils {
	public static void setStatusBarColor(Activity activity) {
		Window window = activity.getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
	}
}
