package de.feuerwehraumuehle.feuerwehrapp.manager;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import de.feuerwehraumuehle.feuerwehrapp.DEISApplication;
import de.feuerwehraumuehle.feuerwehrapp.config.ItemConfiguration;
import de.feuerwehraumuehle.feuerwehrapp.config.parser.ItemParser;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;
import de.feuerwehraumuehle.feuerwehrapp.model.ItemType;
import de.feuerwehraumuehle.feuerwehrapp.model.Link;

/**
 * Created by Matze on 19.02.2017.
 */

public class FileManager {

    private static FileManager instance;

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    private Item rootItem;

    public void init() throws Exception {
        DEISApplication.checkIfFolderExistsAndContainsSomething(DEISApplication.getDataPath());
        File dataDirectory = new File(DEISApplication.getDataPath());
        rootItem = new Item();
        scanDirectory(dataDirectory, rootItem);
    }

    private void scanDirectory(File dataDirectory, Item currentItem) throws Exception {
        File[] allDataFiles = dataDirectory.listFiles();

        HashMap<String, ItemConfiguration> itemConfigurations = loadItemConfigurations(allDataFiles);

        for (File file : allDataFiles) {
            if (!file.getName().toUpperCase().endsWith(".CFG")) {
                Item newItem = new Item();
                newItem.setAbsolutePath(file.getAbsolutePath());
                if (file.isDirectory()) {
                    newItem.setType(ItemType.DIRECTORY);
                    scanDirectory(file, newItem);
                } else if (file.getName().toUpperCase().endsWith(".PDF")) {
                    newItem.setType(ItemType.PDF);
                } else if (file.getName().toUpperCase().endsWith(".JPG")) {
                    newItem.setType(ItemType.IMAGE);
                } else if (file.getName().toUpperCase().endsWith(".LINK")) {
                    String packageName = file.getName().substring(
                            file.getName().indexOf("...") + "...".length(),
                            file.getName().length()-".LINK".length());
                    newItem = new Link(packageName);
                    newItem.setType(ItemType.LINK);
                } else {
                    newItem.setType(ItemType.UNDEFINED);
                }
                String rawName = file.getName();
                if (rawName.contains(".")) {
                    int i = rawName.lastIndexOf(".");
                    rawName = rawName.substring(0, i);
                }
                newItem.setDisplayName(rawName);
                newItem.setRawName(rawName);
                if (itemConfigurations.containsKey(rawName)) {
                    ItemConfiguration configuration = itemConfigurations.get(rawName);
                    newItem.setButtonColor(configuration.buttonColor);
                    newItem.setTextColor(configuration.textColor);
                    newItem.setDisplayName(configuration.displayName != null ? configuration.displayName : newItem
                            .getDisplayName());
                    newItem.setIcon(configuration.icon);
                } else {
                    newItem.setButtonColor(GlobalConfigurationsManager.globalDefaults.defaultButtonColor);
                    newItem.setTextColor(GlobalConfigurationsManager.globalDefaults.defaultTextColor);
                    newItem.setIcon(GlobalConfigurationsManager.globalDefaults.defaultIcon);
                }
                if (newItem.getType() != ItemType.UNDEFINED) {
                    currentItem.addChildren(newItem);
                }
            }
        }
        Collections.sort(currentItem.getChildren(), new Comparator<Item>() {
            @Override
            public int compare(Item s1, Item s2) {
                return s1.getRawName().compareToIgnoreCase(s2.getRawName());
            }
        });
    }

    @NonNull
    private HashMap<String, ItemConfiguration> loadItemConfigurations(File[] allDataFiles) throws Exception {
        HashMap<String, ItemConfiguration> itemConfigurations = new HashMap<>();

        ItemParser parser = new ItemParser();
        for (File file : allDataFiles) {
            if (file.getName().toUpperCase().endsWith(".CFG")) {
                ItemConfiguration config = parser.parse(file);
                if (config != null) {
                    itemConfigurations.put(file.getName().substring(0, file.getName().lastIndexOf(".")), config);
                }
            }
        }
        return itemConfigurations;
    }

    public Item getRootItem() {
        return rootItem;
    }
}
