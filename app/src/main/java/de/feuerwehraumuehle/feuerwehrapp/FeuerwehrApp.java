package de.feuerwehraumuehle.feuerwehrapp;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import de.feuerwehraumuehle.feuerwehrapp.config.ColorMap;
import de.feuerwehraumuehle.feuerwehrapp.config.parser.ColorParser;

/**
 * Created by mmi on 01.02.2018.
 */

public class FeuerwehrApp extends Application {

	public static ColorMap colorMap;

	@Override
	public void onCreate() {
		super.onCreate();
		loadColors();
	}

	private void loadColors() {
		File colorsFile = new File(getSDCardPath(), "feuerwehr/config/colors.cfg");
		ColorParser parser = new ColorParser();
		colorMap = (ColorMap) parser.parse(colorsFile);
	}

	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
}
