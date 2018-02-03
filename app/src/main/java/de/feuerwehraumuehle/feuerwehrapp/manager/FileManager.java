package de.feuerwehraumuehle.feuerwehrapp.manager;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import de.feuerwehraumuehle.feuerwehrapp.config.ItemConfiguration;
import de.feuerwehraumuehle.feuerwehrapp.config.parser.ItemParser;
import de.feuerwehraumuehle.feuerwehrapp.exceptions.SeriousConfigurationIssueException;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;
import de.feuerwehraumuehle.feuerwehrapp.model.ItemType;

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

        for (File file : files) {
            if (file.getName().toUpperCase().endsWith(".CFG")) {
                ItemParser parser = new ItemParser();
                ItemConfiguration config = (ItemConfiguration) parser.parse(file);
                if (config != null) {
                    cfgs.put(file.getName().substring(0, file.getName().indexOf(".")), config);
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
                    newItem.setButtonColor(ConfigurationManager.globalDefaults.defaultButtonColor);
                    newItem.setTextColor(ConfigurationManager.globalDefaults.defaultTextColor);
                    newItem.setIcon(ConfigurationManager.globalDefaults.defaultIcon);
                }
                if (newItem.getType() != ItemType.UNDEFINED) {
                    currentFDirectory.addChildren(newItem);
                }
            }
        }
    }

    public Item getRootItem() {
        return rootItem;
    }
}
