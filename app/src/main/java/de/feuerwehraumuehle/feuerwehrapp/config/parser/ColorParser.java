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

	private final String TAG_ROOT = "colors";
	private final String TAG_ATTRIBUTE_NAME = "name";
	private final String TAG_COLOR = "color";

	@Override
	public ColorMap parse(File file) throws Exception {
		return (ColorMap) super.parse(file);
	}

	protected Configuration map(XmlPullParser parser) throws Exception {
		parser.require(XmlPullParser.START_TAG, ns, TAG_ROOT);
		ColorMap colorMap = new ColorMap();
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(TAG_COLOR)) {
				String colorName = parser.getAttributeValue(null, TAG_ATTRIBUTE_NAME);
				String colorValue = readAttribute(parser, TAG_COLOR);
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
