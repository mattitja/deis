package de.feuerwehraumuehle.feuerwehrapp.model;

/**
 * Created by Matze on 18.02.2017.
 */

public class Item {
    private String name;
    private String fullPath;
    private ItemType type;

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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
