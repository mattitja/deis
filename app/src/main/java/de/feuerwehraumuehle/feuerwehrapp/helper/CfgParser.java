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

	public Configuration parse(File in) {
		try {
			InputStream stream = new FileInputStream(in);

			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

			parser.setInput(stream, null);
			parser.nextTag();
			return readConfiguration(parser);
		} catch (Exception e) {
			return null;
		}
	}

	public static class Configuration {
		public final String color;
		public final String alternativeName;

		private Configuration(String color, String alternativeName) {
			this.color = color;
			this.alternativeName = alternativeName;
		}
	}

	private Configuration readConfiguration(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "configuration");
		String color = null;
		String alternativeName = null;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("color")) {
				color = readAttribute(parser, "color");
			} else if (name.equals("alternativeName")) {
				alternativeName = readAttribute(parser, "alternativeName");
			} else{
				skip(parser);

			}
		}
		return new Configuration(color, alternativeName);
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
