package de.miroit.deis.config;

/**
 * Created by mmi on 31.01.2018.
 */

public class ItemConfiguration extends Configuration {

	public final int buttonColor;
	public final String displayName;
	public final int textColor;
	public final String icon;

	public ItemConfiguration(int buttonColor, String displayName, int textColor, String icon) {
		this.buttonColor = buttonColor;
		this.displayName = displayName;
		this.textColor = textColor;
		this.icon = icon;
	}
}
