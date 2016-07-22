package mx.zetta.adf.commons;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

/**
 * The Class MessageServiceEJB.
 */
@Service
public class MessagesService {

	/** The Constant APP_BUNDLE. */
	public static final String APP_BUNDLE = "infra/messagesResources";

	/** The Constant LOGGER. */
	public static final Logger LOGGER = Logger.getLogger(MessagesService.class.getName());

	/** The locale. */
	private Locale localeDefault;

	/**
	 * Start.
	 */
	@PostConstruct
	void start() {
		localeDefault = new Locale("es", "MX");

	}

	public String getString(String resourceId) {
		return getString(APP_BUNDLE, resourceId, null, null);

	}

	public String getString(String resoureBundle, String resourceId) {
		return getString(resoureBundle, resourceId, null, null);

	}

	public String getString(String resourceId, Object[] params, Locale locale) {
		return getString(APP_BUNDLE, resourceId, params, locale);
	}

	public String getString(String resoureBundle, String resourceId, Object[] params, Locale localeParam) {
		Locale locale = localeParam;
		String resource = null;
		ResourceBundle resourceBundle = null;
		if (locale == null) {
			resourceBundle = ResourceBundle.getBundle(resoureBundle);
			locale = localeDefault;
		} else {
			resourceBundle = ResourceBundle.getBundle(resoureBundle, locale);
		}
		try {
			resource = resourceBundle.getString(resourceId);
		} catch (MissingResourceException ex) {
			LOGGER.log(Level.WARNING, ex.getMessage(), ex);
			resource = null;
		}
		if (resource == null) {
			return null; // no match
		}
		if (params == null) {
			return resource;
		}

		MessageFormat formatter = new MessageFormat(resource, locale);
		return formatter.format(params);
	}
}
