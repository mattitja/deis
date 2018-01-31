package de.feuerwehraumuehle.feuerwehrapp.helper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by mmi on 31.01.2018.
 */

public class ColorParser extends AbstractConfigurationParser {

	@Override
	Configuration readConfiguration(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "colors");
		ColorConfiguration colorMap = new ColorConfiguration();
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("color")) {
				String colorName = parser.getAttributeValue(null, "name");
				String colorValue = readAttribute(parser, "color");
				if (colorName != null && colorValue != null) {
					colorMap.addColor(colorName, colorValue);
				}
			} else {
				skip(parser);

			}
		}
		return colorMap;
	}
}
