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

public abstract class AbstractConfigurationParser {

	static final String ns = null;

	public Configuration parse(File in) {
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

	abstract Configuration readConfiguration(XmlPullParser parser) throws XmlPullParserException, IOException;

	String readAttribute(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
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

	void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
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
