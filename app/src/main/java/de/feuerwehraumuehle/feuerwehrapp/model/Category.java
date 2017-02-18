package de.feuerwehraumuehle.feuerwehrapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Matze on 18.02.2017.
 */

public class Category implements Serializable {
    private String name;
    private ArrayList<Item> items;

    public Category(String name) {
        this.name = name;
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
