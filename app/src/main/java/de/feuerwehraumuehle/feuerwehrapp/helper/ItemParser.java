package de.feuerwehraumuehle.feuerwehrapp.helper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by mmi on 31.01.2018.
 */

public class ItemParser extends AbstractConfigurationParser {

	Configuration readConfiguration(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "item");
		String buttonColor = null;
		String displayName = null;
		String textColor = null;
		String icon = null;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("buttonColor")) {
				buttonColor = readAttribute(parser, "buttonColor");
			} else if (name.equals("displayName")) {
				displayName = readAttribute(parser, "displayName");
			} else if (name.equals("textColor")) {
				textColor = readAttribute(parser, "textColor");
			} else if (name.equals("icon")) {
				icon = readAttribute(parser, "icon");
			} else {
				skip(parser);

			}
		}
		return new ItemConfiguration(buttonColor, displayName, textColor, icon);
	}
}
