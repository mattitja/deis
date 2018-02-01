package de.feuerwehraumuehle.feuerwehrapp;

import android.app.Application;
import android.graphics.Color;
import android.os.Environment;

import java.io.File;

import de.feuerwehraumuehle.feuerwehrapp.config.ColorMap;
import de.feuerwehraumuehle.feuerwehrapp.config.GlobalDefaults;
import de.feuerwehraumuehle.feuerwehrapp.config.parser.ColorParser;
import de.feuerwehraumuehle.feuerwehrapp.config.parser.DefaultsParser;

/**
 * Created by mmi on 01.02.2018.
 */

public class FeuerwehrApp extends Application {

	public static ColorMap colorMap;
	public static GlobalDefaults globalDefaults;

	@Override
	public void onCreate() {
		super.onCreate();
		loadColors();
		loadGlobalDefaults();
	}

	private void loadColors() {
		File colorsFile = new File(getSDCardPath(), "feuerwehr/config/colors.cfg");
		ColorParser parser = new ColorParser();
		colorMap = parser.parse(colorsFile);
	}

	private void loadGlobalDefaults() {
		File colorsFile = new File(getSDCardPath(), "feuerwehr/config/defaults.cfg");
		DefaultsParser parser = new DefaultsParser();
		globalDefaults = parser.parse(colorsFile);
	}

	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	public static int getColorByColorSomething(String colorName) {
		int buttonColor = 0;
		String colorValue = colorMap.getColorByName(colorName);
		if (colorValue != null) {
			buttonColor = Color.parseColor(colorValue);
		} else {
			buttonColor = Color.parseColor(colorName);
		}
		return buttonColor;
	}

	public static int getColorByColorSomething(String colorSomething, int defaultColor) {
		int color = getColorByColorSomething(colorSomething);
		if (color == 0) {
			color = defaultColor;
		}
		return color;
	}
}
