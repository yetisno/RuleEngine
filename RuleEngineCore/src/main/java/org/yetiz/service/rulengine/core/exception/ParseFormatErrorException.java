package org.yetiz.service.rulengine.core.exception;

/**
 * Created by yeti on 2014/12/1.
 */
public class ParseFormatErrorException extends RuleEngineRuntimeException {
	private static final long serialVersionUID = 3067165165450540613L;
	public static final String CODE = "RE0002";

	public ParseFormatErrorException() {
		super("Object can't convert to target Format.", CODE);
	}

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public ParseFormatErrorException(String message) {
		super("Object can't convert to target Format. " + message, CODE);
	}
}
