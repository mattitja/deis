package de.feuerwehraumuehle.feuerwehrapp.config.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

import de.feuerwehraumuehle.feuerwehrapp.FeuerwehrApp;
import de.feuerwehraumuehle.feuerwehrapp.config.Configuration;
import de.feuerwehraumuehle.feuerwehrapp.config.GlobalDefaults;

/**
 * Created by mmi on 31.01.2018.
 */

public class DefaultsParser extends AbstractConfigurationParser {

	@Override
	public GlobalDefaults parse(File file) {
		return (GlobalDefaults) super.parse(file);
	}

	protected Configuration map(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "defaults");
		int defaultButtonColor = -1;
		int defaultTextColor = -1;
		String defaultIcon = null;
		int defaultBackgroundColor = -1;
		boolean randomizeAllButtonColors = false;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("buttonColor")) {
				defaultButtonColor = FeuerwehrApp.getColorByColorSomething(readAttribute(parser, "buttonColor"));
			} else if (name.equals("backgroundColor")) {
				defaultBackgroundColor = FeuerwehrApp.getColorByColorSomething(readAttribute(parser, "backgroundColor"));
			} else if (name.equals("textColor")) {
				defaultTextColor = FeuerwehrApp.getColorByColorSomething(readAttribute(parser, "textColor"));
			} else if (name.equals("icon")) {
				defaultIcon = readAttribute(parser, "icon");
			} else if (name.equals("randomizeAllButtonColors")) {
				String bool = readAttribute(parser, "randomizeAllButtonColors");
				randomizeAllButtonColors = "true".equalsIgnoreCase(bool);
			} else {
				skip(parser);
			}
		}

		checkIfDefaultIsSet(defaultBackgroundColor);
		checkIfDefaultIsSet(defaultButtonColor);
		checkIfDefaultIsSet(defaultTextColor);

		return new GlobalDefaults(defaultButtonColor, defaultTextColor, defaultIcon, defaultBackgroundColor, randomizeAllButtonColors);
	}

	private void checkIfDefaultIsSet(int color) {
		if (color == 0) {
			throw new NoDefaultValueException();
		}
	}

	private class NoDefaultValueException extends RuntimeException {
	}
}
