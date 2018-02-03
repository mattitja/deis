package de.feuerwehraumuehle.feuerwehrapp.model;

import java.util.ArrayList;

/**
 * Created by Matze on 19.02.2017.
 */

public class Item {

    private String displayName;
    private String absolutePath;
    private ItemType type;
    private ArrayList<Item> children;
    private int buttonColor;
    private int textColor;
    private String icon;
    private String rawName;

    public Item() {
        children = new ArrayList<>();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public ArrayList<Item> getChildren() {
        return children;
    }

    public void addChildren(Item child) {
        children.add(child);
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setButtonColor(int buttonColor) {
        this.buttonColor = buttonColor;
    }

    public int getButtonColor() {
        return buttonColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String iconName) {
        this.icon = iconName;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }
}
