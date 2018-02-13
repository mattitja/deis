package de.miroit.deis.config.parser;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;

import de.miroit.deis.config.Configuration;
import de.miroit.deis.config.GlobalDefaults;
import de.miroit.deis.exceptions.SeriousConfigurationIssueException;
import de.miroit.deis.manager.GlobalConfigurationsManager;

/**
 * Created by mmi on 31.01.2018.
 */

public class DefaultsParser extends AbstractConfigurationParser {

	private final String TAG_ROOT = "defaults";
	private final String TAG_BUTTON_COLOR = "buttonColor";
	private final String TAG_BACKGROUND_COLOR = "backgroundColor";
	private final String TAG_TEXT_COLOR = "textColor";
	private final String TAG_ICON = "icon";
	private final String TAG_MENU_BACKGROUND_COLOR = "menuBackgroundColor";

	@Override
	public GlobalDefaults parse(File file) throws Exception {
		return (GlobalDefaults) super.parse(file);
	}

	protected Configuration map(XmlPullParser parser) throws Exception {
		parser.require(XmlPullParser.START_TAG, ns, TAG_ROOT);
		int defaultButtonColor = 0;
		int defaultTextColor = 0;
		String defaultIcon = null;
		int defaultBackgroundColor = 0;
		int defaultMenuBackgroundColor = 0;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals(TAG_BUTTON_COLOR)) {
				defaultButtonColor = GlobalConfigurationsManager.getColorByColorSomething(readAttribute(parser, TAG_BUTTON_COLOR));
			} else if (name.equals(TAG_BACKGROUND_COLOR)) {
				defaultBackgroundColor = GlobalConfigurationsManager.getColorByColorSomething(readAttribute(parser, TAG_BACKGROUND_COLOR));
			} else if (name.equals(TAG_TEXT_COLOR)) {
				defaultTextColor = GlobalConfigurationsManager.getColorByColorSomething(readAttribute(parser, TAG_TEXT_COLOR));
			} else if (name.equals(TAG_ICON)) {
				defaultIcon = readAttribute(parser, TAG_ICON);
			} else if (name.equals(TAG_MENU_BACKGROUND_COLOR)) {
				defaultMenuBackgroundColor = GlobalConfigurationsManager.getColorByColorSomething(readAttribute(parser, TAG_MENU_BACKGROUND_COLOR));
			} else {
				skip(parser);
			}
		}

		checkIfDefaultIsSet(TAG_BUTTON_COLOR, defaultButtonColor);
		checkIfDefaultIsSet(TAG_BACKGROUND_COLOR, defaultBackgroundColor);
		checkIfDefaultIsSet(TAG_TEXT_COLOR, defaultTextColor);
		checkIfDefaultIsSet(TAG_MENU_BACKGROUND_COLOR, defaultMenuBackgroundColor);

		return new GlobalDefaults(defaultButtonColor, defaultTextColor, defaultIcon, defaultBackgroundColor, defaultMenuBackgroundColor);
	}

	private void checkIfDefaultIsSet(String propertyName, int color) throws SeriousConfigurationIssueException {
		if (color == 0) {
			throw new SeriousConfigurationIssueException("Kein (gültigen) default-Wert für " + propertyName + " gefunden.\n" +
					"Stelle Sicher, dass in der defaults.cfg der Wert hinterlegt und gültig ist.");
		}
	}
}
