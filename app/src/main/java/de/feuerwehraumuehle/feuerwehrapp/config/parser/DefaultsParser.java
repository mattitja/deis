package de.feuerwehraumuehle.feuerwehrapp.config.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

import de.feuerwehraumuehle.feuerwehrapp.config.Configuration;
import de.feuerwehraumuehle.feuerwehrapp.config.GlobalDefaults;
import de.feuerwehraumuehle.feuerwehrapp.exceptions.SeriousConfigurationIssueException;
import de.feuerwehraumuehle.feuerwehrapp.manager.ConfigurationManager;

/**
 * Created by mmi on 31.01.2018.
 */

public class DefaultsParser extends AbstractConfigurationParser {

	@Override
	public GlobalDefaults parse(File file) throws Exception {
		return (GlobalDefaults) super.parse(file);
	}

	protected Configuration map(XmlPullParser parser) throws Exception {
		parser.require(XmlPullParser.START_TAG, ns, "defaults");
		int defaultButtonColor = 0;
		int defaultTextColor = 0;
		String defaultIcon = null;
		int defaultBackgroundColor = 0;
		int defaultMenuBackgroundColor = 0;
		boolean randomizeAllButtonColors = false;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("buttonColor")) {
				defaultButtonColor = ConfigurationManager.getColorByColorSomething(readAttribute(parser, "buttonColor"));
			} else if (name.equals("backgroundColor")) {
				defaultBackgroundColor = ConfigurationManager.getColorByColorSomething(readAttribute(parser, "backgroundColor"));
			} else if (name.equals("textColor")) {
				defaultTextColor = ConfigurationManager.getColorByColorSomething(readAttribute(parser, "textColor"));
			} else if (name.equals("icon")) {
				defaultIcon = readAttribute(parser, "icon");
			} else if (name.equals("menuBackgroundColor")) {
				defaultMenuBackgroundColor = ConfigurationManager.getColorByColorSomething(readAttribute(parser, "menuBackgroundColor"));
			} else if (name.equals("randomizeAllButtonColors")) {
				String bool = readAttribute(parser, "randomizeAllButtonColors");
				randomizeAllButtonColors = "true".equalsIgnoreCase(bool);
			} else {
				skip(parser);
			}
		}

		checkIfDefaultIsSet("backgroundColor", defaultBackgroundColor);
		checkIfDefaultIsSet("buttonColor", defaultButtonColor);
		checkIfDefaultIsSet("textColor", defaultTextColor);
		checkIfDefaultIsSet("menuBackgroundColor", defaultMenuBackgroundColor);

		return new GlobalDefaults(defaultButtonColor, defaultTextColor, defaultIcon, defaultBackgroundColor, defaultMenuBackgroundColor, randomizeAllButtonColors);
	}

	private void checkIfDefaultIsSet(String propertyName, int color) throws SeriousConfigurationIssueException {
		if (color == 0) {
			throw new SeriousConfigurationIssueException("Kein (gültigen) default-Wert für " + propertyName + " gefunden.\n" +
					"Stelle Sicher, dass in der defaults.cfg der Wert hinterlegt und gültig ist.");
		}
	}
}
