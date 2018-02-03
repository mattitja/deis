package de.feuerwehraumuehle.feuerwehrapp.model;

/**
 * Created by mmi on 03.02.2018.
 */

public class Link extends Item {

	private String packageName;

	public Link(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
