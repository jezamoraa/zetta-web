package mx.zetta.adf.commons;

import java.util.Map;
import java.util.TreeMap;

public class ApplicationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	protected Map<String, String> messages;

	public ApplicationException() {
		super();
		messages = new TreeMap<String, String>();
	}

	public ApplicationException(String message) {
		super(message);
		messages = new TreeMap<String, String>();
	}

	public ApplicationException(Map<String, String> messages) {
		this.messages = messages;
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}
}
