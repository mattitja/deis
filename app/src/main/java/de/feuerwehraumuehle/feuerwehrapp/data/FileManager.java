package de.feuerwehraumuehle.feuerwehrapp.data;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.HashMap;

import de.feuerwehraumuehle.feuerwehrapp.helper.CfgParser;
import de.feuerwehraumuehle.feuerwehrapp.model.FFile;
import de.feuerwehraumuehle.feuerwehrapp.model.FFileType;

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

    private FFile rootFFile;

    private FileManager(Context context) throws StartFolderNotFoundException,
            StartFolderContainsNoItems {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String sdcardPath = externalStorageDirectory.getAbsolutePath();
        File startFolder = new File(sdcardPath, "feuerwehr/data");
        if (startFolder.isDirectory() && startFolder.listFiles().length == 0) {
            throw new StartFolderNotFoundException();
        } else if(startFolder.listFiles().length == 0) {
            throw new StartFolderContainsNoItems();
        }
        rootFFile = new FFile();

        scanDirectory(startFolder, rootFFile);
    }

    public FFile getRootFFile() {
        return rootFFile;
    }

    private void scanDirectory(File directory, FFile currentFDirectory) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        // CFG Handling
        HashMap<String, CfgParser.ItemConfiguration> cfgs = new HashMap<>();

        for (File file : files) {
            if (file.getName().toUpperCase().endsWith(".CFG")) {
                CfgParser parser = new CfgParser();
                CfgParser.ItemConfiguration config = parser.parse(file);
                cfgs.put(file.getName().substring(0, file.getName().indexOf(".")), config);
            }
        }
        for (File file : files) {
            if (!file.getName().toUpperCase().endsWith(".CFG")) {
                FFile newFFile = new FFile();
                newFFile.setAbsolutePath(file.getAbsolutePath());
                if (file.isDirectory()) {
                    newFFile.setType(FFileType.DIRECTORY);
                    scanDirectory(file, newFFile);
                } else if (file.getName().toUpperCase().endsWith(".PDF")) {
                    newFFile.setType(FFileType.PDF);
                } else if (file.getName().toUpperCase().endsWith(".JPG")) {
                    newFFile.setType(FFileType.IMAGE);
                } else {
                    newFFile.setType(FFileType.UNDEFINED);
                }
                String name = file.getName();
                if (name.contains(".")) {
                    int i = name.indexOf(".");
                    name = name.substring(0, i);
                }
                newFFile.setDisplayName(name);
                if (cfgs.containsKey(name)) {
                    CfgParser.ItemConfiguration configuration = cfgs.get(name);
                    newFFile.setButtonColor(configuration.buttonColor);
                    newFFile.setTextColor(configuration.textColor);
                    newFFile.setDisplayName(configuration.displayName != null ? configuration.displayName : newFFile
                            .getDisplayName());
                    newFFile.setIcon(configuration.icon);
                }
                if (newFFile.getType() != FFileType.UNDEFINED) {
                    currentFDirectory.addChildren(newFFile);
                }
            }
        }
    }

    public class StartFolderNotFoundException extends Exception {
    }

    public class StartFolderContainsNoItems extends Exception {
    }
}
