package de.feuerwehraumuehle.feuerwehrapp.manager;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

import de.feuerwehraumuehle.feuerwehrapp.config.ColorMap;
import de.feuerwehraumuehle.feuerwehrapp.config.GlobalDefaults;
import de.feuerwehraumuehle.feuerwehrapp.config.parser.ColorParser;
import de.feuerwehraumuehle.feuerwehrapp.config.parser.DefaultsParser;

/**
 * Created by Matze on 03.02.2018.
 */

public class ConfigurationManager {

    private static ConfigurationManager instance;

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public static ColorMap colorMap;
    public static GlobalDefaults globalDefaults;

    public void init() throws Exception {
        loadColors();
        loadGlobalDefaults();
    }

    private void loadColors() throws Exception {
        File colorsFile = new File(getSDCardPath(), "feuerwehr/config/colors.cfg");
        ColorParser parser = new ColorParser();
        colorMap = parser.parse(colorsFile);
    }

    private void loadGlobalDefaults() throws Exception {
        File colorsFile = new File(getSDCardPath(), "feuerwehr/config/defaults.cfg");
        DefaultsParser parser = new DefaultsParser();
        globalDefaults = parser.parse(colorsFile);
    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static int getColorByColorSomething(String colorName) {
        int buttonColor = 0;
        try {
            String colorValue = colorMap.getColorByName(colorName);
            if (colorValue != null) {
                buttonColor = Color.parseColor(colorValue);
            } else {
                buttonColor = Color.parseColor(colorName);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return buttonColor;
    }
}
