package de.feuerwehraumuehle.feuerwehrapp.model;

import java.util.ArrayList;

/**
 * Created by Matze on 19.02.2017.
 */

public class FFile {
    private String displayName;
    private String absolutePath;
    private FFileType type;
    private ArrayList<FFile> children;
    private String buttonColor;
    private String textColor;
    private String icon;

    public FFile() {
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

    public ArrayList<FFile> getChildren() {
        return children;
    }

    public void addChildren(FFile child) {
        children.add(child);
    }

    public FFileType getType() {
        return type;
    }

    public void setType(FFileType type) {
        this.type = type;
    }

    public void setButtonColor(String buttonColor) {
        this.buttonColor = buttonColor;
    }

    public String getButtonColor() {
        return buttonColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String iconName) {
        this.icon = iconName;
    }
}
