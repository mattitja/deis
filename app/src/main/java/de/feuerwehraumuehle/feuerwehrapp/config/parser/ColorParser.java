package de.feuerwehraumuehle.feuerwehrapp.config.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

import de.feuerwehraumuehle.feuerwehrapp.config.ColorMap;
import de.feuerwehraumuehle.feuerwehrapp.config.Configuration;

/**
 * Created by mmi on 31.01.2018.
 */

public class ColorParser extends AbstractConfigurationParser {

	@Override
	public ColorMap parse(File file) throws Exception {
		return (ColorMap) super.parse(file);
	}

	protected Configuration map(XmlPullParser parser) throws Exception {
		parser.require(XmlPullParser.START_TAG, ns, "colors");
		ColorMap colorMap = new ColorMap();
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
