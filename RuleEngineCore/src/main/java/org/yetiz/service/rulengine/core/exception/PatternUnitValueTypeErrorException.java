package org.yetiz.service.rulengine.core.exception;

/**
 * Created by yeti on 2014/12/1.
 */
public class PatternUnitValueTypeErrorException extends RuleEngineRuntimeException {
	private static final long serialVersionUID = 7099186233660774362L;
	private static final String MESSAGE = "The type of set value is NOT accessable.";
	public static final String CODE = "RE0008";

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 */
	public PatternUnitValueTypeErrorException() {
		super(MESSAGE, CODE);
	}

	public PatternUnitValueTypeErrorException(String message) {
		super(MESSAGE + message, CODE);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param object the detail object. The detail message is saved for
	 *               later retrieval by the {@link #getMessage()} method.
	 */
	public PatternUnitValueTypeErrorException(Object object) {
		super(MESSAGE, object, CODE);
	}
}
