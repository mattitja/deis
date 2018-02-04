package de.feuerwehraumuehle.feuerwehrapp;

import android.app.Application;
import android.os.Environment;

/**
 * Created by mmi on 04.02.2018.
 */

public class DEISApplication extends Application {

	private static final String rootFolder = "/feuerwehr";

	private static final String dataRelativeFolder = rootFolder + "/data";
	private static final String configRelativeFolder = rootFolder + "/config";
	private static final String iconsRelativeFolder = configRelativeFolder + "/icons";

	private static String sdcardPath;

	@Override
	public void onCreate() {
		super.onCreate();
		sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	public static String getDataPath() {
		return sdcardPath + dataRelativeFolder;
	}

	public static String getConfigPath() {
		return sdcardPath + configRelativeFolder;
	}

	public static String getIconsPath() {
		return sdcardPath + iconsRelativeFolder;
	}
}
