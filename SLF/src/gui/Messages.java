package gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import constance.Dialogs;
import constance.YesNo;

public class Messages {
	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static YesNo ShowMessage(String message, Dialogs type) {
		DialogPanel myDialog=new DialogPanel(message, type);
		YesNo temp=myDialog.GetResult();
		myDialog.dispose();
		return temp;
	}
}
