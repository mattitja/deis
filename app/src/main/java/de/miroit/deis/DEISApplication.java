package de.miroit.deis;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import de.miroit.deis.exceptions.SeriousConfigurationIssueException;

/**
 * Created by mmi on 04.02.2018.
 */

public class DEISApplication extends Application {

	private static final String rootFolder = "/deis";

	private static final String dataRelativeFolder = rootFolder + "/data";
	private static final String configRelativeFolder = rootFolder + "/config";
	private static final String iconsRelativeFolder = configRelativeFolder + "/icons";

	private static String sdcardPath;

	@Override
	public void onCreate() {
		super.onCreate();
		sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	public static String getRootPath() {
		return sdcardPath + rootFolder;
	}

	public static String getDataPath() {
		return sdcardPath + dataRelativeFolder;
	}

	public static String getConfigPath() {
		return sdcardPath + configRelativeFolder;
	}

	public static String getIconsPath() {
		return sdcardPath + iconsRelativeFolder;
	}

	public static void checkIfFolderExistsAndContainsSomething(String path) throws
			SeriousConfigurationIssueException {
		File pathDirectory = new File(path);
		if (!pathDirectory.exists() || !pathDirectory.isDirectory()) {
			String msg = "Der Ordner " + path + " existiert nicht.";
			throw new SeriousConfigurationIssueException(msg);
		} else if (pathDirectory.listFiles().length == 0) {
			String msg = "Der Ordner " + path + " beinhaltet nichts.";
			throw new SeriousConfigurationIssueException(msg);
		}
	}
}
