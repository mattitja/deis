package de.feuerwehraumuehle.feuerwehrapp.helper;

/**
 * Created by mmi on 31.01.2018.
 */

public class ItemConfiguration extends Configuration {

	public final String buttonColor;
	public final String displayName;
	public final String textColor;
	public final String icon;

	public ItemConfiguration(String color, String displayName, String textColor, String icon) {
		this.buttonColor = color;
		this.displayName = displayName;
		this.textColor = textColor;
		this.icon = icon;
	}
}
