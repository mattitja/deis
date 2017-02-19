package de.feuerwehraumuehle.feuerwehrapp.model;

import java.io.Serializable;

/**
 * Created by Matze on 18.02.2017.
 */

public class Item implements Serializable {
    private String name;
    private String fullPath;
    private FFileType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public FFileType getType() {
        return type;
    }

    public void setType(FFileType type) {
        this.type = type;
    }
}
