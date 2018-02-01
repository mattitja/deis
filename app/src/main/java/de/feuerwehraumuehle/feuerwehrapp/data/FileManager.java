package de.feuerwehraumuehle.feuerwehrapp.data;

import android.content.Context;

import java.io.File;
import java.util.HashMap;

import de.feuerwehraumuehle.feuerwehrapp.FeuerwehrApp;
import de.feuerwehraumuehle.feuerwehrapp.config.ItemConfiguration;
import de.feuerwehraumuehle.feuerwehrapp.config.parser.ItemParser;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;
import de.feuerwehraumuehle.feuerwehrapp.model.ItemType;

/**
 * Created by Matze on 19.02.2017.
 */

public class FileManager {

    private static FileManager instance;

    public static FileManager getInstance(Context context) throws StartFolderNotFoundException, StartFolderContainsNoItems {
        if (instance == null) {
            instance = new FileManager(context);
        }
        return instance;
    }

    private Item rootItem;

    private FileManager(Context context) throws StartFolderNotFoundException,
            StartFolderContainsNoItems {
        String sdcardPath = FeuerwehrApp.getSDCardPath();
        File dataStartFolder = new File(sdcardPath, "feuerwehr/data");
        if (dataStartFolder.isDirectory() && dataStartFolder.listFiles().length == 0) {
            throw new StartFolderNotFoundException();
        } else if(dataStartFolder.listFiles().length == 0) {
            throw new StartFolderContainsNoItems();
        }
        rootItem = new Item();

        scanDirectory(dataStartFolder, rootItem);
    }

    public Item getRootItem() {
        return rootItem;
    }

    private void scanDirectory(File directory, Item currentFDirectory) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        // CFG Handling
        HashMap<String, ItemConfiguration> cfgs = new HashMap<>();

        for (File file : files) {
            if (file.getName().toUpperCase().endsWith(".CFG")) {
                ItemParser parser = new ItemParser();
                ItemConfiguration config = (ItemConfiguration) parser.parse(file);
                cfgs.put(file.getName().substring(0, file.getName().indexOf(".")), config);
            }
        }
        for (File file : files) {
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
                } else {
                    newItem.setType(ItemType.UNDEFINED);
                }
                String name = file.getName();
                if (name.contains(".")) {
                    int i = name.indexOf(".");
                    name = name.substring(0, i);
                }
                newItem.setDisplayName(name);
                if (cfgs.containsKey(name)) {
                    ItemConfiguration configuration = cfgs.get(name);
                    newItem.setButtonColor(configuration.buttonColor);
                    newItem.setTextColor(configuration.textColor);
                    newItem.setDisplayName(configuration.displayName != null ? configuration.displayName : newItem
                            .getDisplayName());
                    newItem.setIcon(configuration.icon);
                } else {
                    newItem.setButtonColor(FeuerwehrApp.globalDefaults.defaultButtonColor);
                    newItem.setTextColor(FeuerwehrApp.globalDefaults.defaultTextColor);
                    newItem.setIcon(FeuerwehrApp.globalDefaults.defaultIcon);
                }
                if (newItem.getType() != ItemType.UNDEFINED) {
                    currentFDirectory.addChildren(newItem);
                }
            }
        }
    }

    public class StartFolderNotFoundException extends Exception {
    }

    public class StartFolderContainsNoItems extends Exception {
    }
}
