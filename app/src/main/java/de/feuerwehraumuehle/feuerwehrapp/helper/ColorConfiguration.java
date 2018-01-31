package de.feuerwehraumuehle.feuerwehrapp.helper;

import java.util.HashMap;

/**
 * Created by mmi on 31.01.2018.
 */

public class ColorConfiguration extends Configuration {

	public ColorConfiguration() {
		colorMap = new HashMap<>();
	}

	private HashMap<String, String> colorMap;

	public void addColor(String name, String hex) {
		colorMap.put(name, hex);
	}

	public String getColorByName(String name) {
		return colorMap.containsKey(name) ? colorMap.get(name) : null;
	}
}
