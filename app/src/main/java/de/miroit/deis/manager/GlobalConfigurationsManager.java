package de.miroit.deis.manager;

import android.graphics.Color;

import java.io.File;

import de.miroit.deis.DEISApplication;
import de.miroit.deis.config.ColorMap;
import de.miroit.deis.config.GlobalDefaults;
import de.miroit.deis.config.parser.ColorParser;
import de.miroit.deis.config.parser.DefaultsParser;

/**
 * Created by Matze on 03.02.2018.
 */

public class GlobalConfigurationsManager {

    private static GlobalConfigurationsManager instance;

    public static GlobalConfigurationsManager getInstance() {
        if (instance == null) {
            instance = new GlobalConfigurationsManager();
        }
        return instance;
    }

    private final static String PATH_COLORS = "colors.cfg";
    private final static String PATH_DEFAULTS = "defaults.cfg";

    public static ColorMap colorMap;
    public static GlobalDefaults globalDefaults;

    public void init() throws Exception {
        DEISApplication.checkIfFolderExistsAndContainsSomething(DEISApplication.getRootPath());
        DEISApplication.checkIfFolderExistsAndContainsSomething(DEISApplication.getConfigPath());
        loadColors();
        loadGlobalDefaults();
    }

    private void loadColors() throws Exception {
        File colorsFile = new File(DEISApplication.getConfigPath(), PATH_COLORS);
        ColorParser parser = new ColorParser();
        colorMap = parser.parse(colorsFile);
    }

    private void loadGlobalDefaults() throws Exception {
        File colorsFile = new File(DEISApplication.getConfigPath(), PATH_DEFAULTS);
        DefaultsParser parser = new DefaultsParser();
        globalDefaults = parser.parse(colorsFile);
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
