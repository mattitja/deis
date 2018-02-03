package de.feuerwehraumuehle.feuerwehrapp.manager;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import de.feuerwehraumuehle.feuerwehrapp.config.ItemConfiguration;
import de.feuerwehraumuehle.feuerwehrapp.config.parser.ItemParser;
import de.feuerwehraumuehle.feuerwehrapp.exceptions.SeriousConfigurationIssueException;
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
        String sdcardPath = ConfigurationManager.getSDCardPath();
        File dataStartFolder = new File(sdcardPath, "feuerwehr/data");
        if (dataStartFolder.isDirectory() && dataStartFolder.listFiles().length == 0) {
            String msg = "Entweder " + sdcardPath + "/feuerwehr existiert nicht oder " +
                    "die Berechtigung " +
                    "\"Speicher\" muss erst noch in den App-Einstellungen gegeben werden.";
            //TODO check for permission
            throw new SeriousConfigurationIssueException(msg);
        } else if(dataStartFolder.listFiles().length == 0) {
            String msg = sdcardPath + "/feuerwehr beinhaltet nichts";
            throw new SeriousConfigurationIssueException(msg);
        }

        rootItem = new Item();
        scanDirectory(dataStartFolder, rootItem);
    }

    private void scanDirectory(File directory, Item currentFDirectory) throws Exception {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        // CFG Handling
        HashMap<String, ItemConfiguration> cfgs = new HashMap<>();

        ItemParser parser = new ItemParser();
        for (File file : files) {
            if (file.getName().toUpperCase().endsWith(".CFG")) {
                ItemConfiguration config = parser.parse(file);
                if (config != null) {
                    cfgs.put(file.getName().substring(0, file.getName().lastIndexOf(".")), config);
                }
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
                if (cfgs.containsKey(rawName)) {
                    ItemConfiguration configuration = cfgs.get(rawName);
                    newItem.setButtonColor(configuration.buttonColor);
                    newItem.setTextColor(configuration.textColor);
                    newItem.setDisplayName(configuration.displayName != null ? configuration.displayName : newItem
                            .getDisplayName());
                    newItem.setIcon(configuration.icon);
                } else {
                    newItem.setButtonColor(ConfigurationManager.globalDefaults.defaultButtonColor);
                    newItem.setTextColor(ConfigurationManager.globalDefaults.defaultTextColor);
                    newItem.setIcon(ConfigurationManager.globalDefaults.defaultIcon);
                }
                if (newItem.getType() != ItemType.UNDEFINED) {
                    currentFDirectory.addChildren(newItem);
                }
            }
        }
        Collections.sort(currentFDirectory.getChildren(), new Comparator<Item>() {
            @Override
            public int compare(Item s1, Item s2) {
                return s1.getRawName().compareToIgnoreCase(s2.getRawName());
            }
        });
    }

    public Item getRootItem() {
        return rootItem;
    }
}
