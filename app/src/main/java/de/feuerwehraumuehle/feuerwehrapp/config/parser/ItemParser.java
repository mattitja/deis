package de.feuerwehraumuehle.feuerwehrapp.config.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

import de.feuerwehraumuehle.feuerwehrapp.config.Configuration;
import de.feuerwehraumuehle.feuerwehrapp.config.ItemConfiguration;
import de.feuerwehraumuehle.feuerwehrapp.manager.ConfigurationManager;

/**
 * Created by mmi on 31.01.2018.
 */

public class ItemParser extends AbstractConfigurationParser {

	@Override
	public ItemConfiguration parse(File file) throws Exception {
		return (ItemConfiguration) super.parse(file);
	}

	protected Configuration map(XmlPullParser parser) throws Exception {
		parser.require(XmlPullParser.START_TAG, ns, "item");
		int buttonColor = 0;
		String displayName = null;
		int textColor = 0;
		String icon = null;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("buttonColor")) {
				buttonColor = ConfigurationManager.getColorByColorSomething(readAttribute(parser, "buttonColor"));
			} else if (name.equals("displayName")) {
				displayName = readAttribute(parser, "displayName");
			} else if (name.equals("textColor")) {
				textColor = ConfigurationManager.getColorByColorSomething(readAttribute(parser, "textColor"));
			} else if (name.equals("icon")) {
				icon = readAttribute(parser, "icon");
			} else {
				skip(parser);

			}
		}
		if (buttonColor == 0) {
			buttonColor = ConfigurationManager.globalDefaults.defaultButtonColor;
		}
		if (textColor == 0) {
			textColor = ConfigurationManager.globalDefaults.defaultTextColor;
		}

		return new ItemConfiguration(buttonColor, displayName, textColor, icon);
	}
}
