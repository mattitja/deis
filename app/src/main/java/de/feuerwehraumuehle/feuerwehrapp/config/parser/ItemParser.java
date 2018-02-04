package de.feuerwehraumuehle.feuerwehrapp.config.parser;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;

import de.feuerwehraumuehle.feuerwehrapp.config.Configuration;
import de.feuerwehraumuehle.feuerwehrapp.config.ItemConfiguration;
import de.feuerwehraumuehle.feuerwehrapp.manager.GlobalConfigurationsManager;

/**
 * Created by mmi on 31.01.2018.
 */

public class ItemParser extends AbstractConfigurationParser {

	private final String TAG_ROOT = "item";
	private final String TAG_BUTTON_COLOR = "buttonColor";
	private final String TAG_DISPLAY_NAME = "displayName";
	private final String TAG_TEXT_COLOR = "textColor";
	private final String TAG_ICON = "icon";

	@Override
	public ItemConfiguration parse(File file) throws Exception {
		return (ItemConfiguration) super.parse(file);
	}

	protected Configuration map(XmlPullParser parser) throws Exception {
		parser.require(XmlPullParser.START_TAG, ns, TAG_ROOT);
		int buttonColor = 0;
		String displayName = null;
		int textColor = 0;
		String icon = null;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(TAG_BUTTON_COLOR)) {
				buttonColor = GlobalConfigurationsManager.getColorByColorSomething(readAttribute(parser, TAG_BUTTON_COLOR));
			} else if (name.equals(TAG_DISPLAY_NAME)) {
				displayName = readAttribute(parser, TAG_DISPLAY_NAME);
			} else if (name.equals(TAG_TEXT_COLOR)) {
				textColor = GlobalConfigurationsManager.getColorByColorSomething(readAttribute(parser, TAG_TEXT_COLOR));
			} else if (name.equals(TAG_ICON)) {
				icon = readAttribute(parser, TAG_ICON);
			} else {
				skip(parser);

			}
		}
		if (buttonColor == 0) {
			buttonColor = GlobalConfigurationsManager.globalDefaults.defaultButtonColor;
		}
		if (textColor == 0) {
			textColor = GlobalConfigurationsManager.globalDefaults.defaultTextColor;
		}

		return new ItemConfiguration(buttonColor, displayName, textColor, icon);
	}
}
