package org.yetiz.service.rulengine.core.exception;

/**
 * Created by yeti on 2014/12/1.
 */
public class PatternUnitNotExistException extends RuleEngineRuntimeException {
	private static final long serialVersionUID = -2206522872865969245L;
	public static final String CODE = "RE0001";

	public PatternUnitNotExistException() {
		super("Pattern Unit of target id is not exist.", CODE);
	}

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public PatternUnitNotExistException(String message) {
		super(message, CODE);
	}
}
