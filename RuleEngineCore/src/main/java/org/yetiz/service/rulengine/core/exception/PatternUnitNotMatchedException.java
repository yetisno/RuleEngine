package org.yetiz.service.rulengine.core.exception;

import java.io.Serializable;

/**
 * Created by yeti on 2014/12/1.
 */
public class PatternUnitNotMatchedException extends RuleEngineRuntimeException {
	private static final long serialVersionUID = -3857265888724338080L;
	private static final String MESSAGE = "No Suitable Pattern Unit Type.";
	public static final String CODE = "RE0007";

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 */
	public PatternUnitNotMatchedException() {
		super(MESSAGE, CODE);
	}

	public PatternUnitNotMatchedException(String message) {
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
	public PatternUnitNotMatchedException(Object object) {
		super(MESSAGE, object, CODE);
	}
}
