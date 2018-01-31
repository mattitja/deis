package de.feuerwehraumuehle.feuerwehrapp.helper;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Matze on 19.02.2017.
 */

public class CfgParser {

	private static final String ns = null;

	public ItemConfiguration parse(File in) {
		try {
			InputStream stream = new FileInputStream(in);
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(stream, null);
			parser.nextTag();
			return readConfiguration(parser);
		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static class ItemConfiguration {
		public final String buttonColor;
		public final String displayName;
        public final String textColor;
        public final String icon;

		private ItemConfiguration(String color, String displayName, String textColor, String icon) {
			this.buttonColor = color;
			this.displayName = displayName;
            this.textColor = textColor;
            this.icon = icon;
		}
	}

	private ItemConfiguration readConfiguration(XmlPullParser parser) throws XmlPullParserException, IOException {
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

	private String readAttribute(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, tag);
		String attr = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, tag);
		return attr;
	}

	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}

	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
				case XmlPullParser.END_TAG:
					depth--;
					break;
				case XmlPullParser.START_TAG:
					depth++;
					break;
			}
		}
	}
}
