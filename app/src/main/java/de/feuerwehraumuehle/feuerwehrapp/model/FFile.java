package de.feuerwehraumuehle.feuerwehrapp.model;

import java.util.ArrayList;

/**
 * Created by Matze on 19.02.2017.
 */

public class FFile {
    private String name;
    private String absolutePath;
    private FFileType type;
    private ArrayList<FFile> children;
    private String color;

    public FFile() {
        children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
