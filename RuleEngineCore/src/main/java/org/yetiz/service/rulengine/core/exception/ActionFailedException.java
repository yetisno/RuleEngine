package org.yetiz.service.rulengine.core.exception;

import org.yetiz.service.rulengine.core.Actional;

/**
 * Created by yeti on 2014/12/1.
 */
public class ActionFailedException extends RuleEngineRuntimeException {
	private static final long serialVersionUID = -1994987884591559953L;
	public static final String CODE = "RE0005";

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public ActionFailedException(String message) {
		super("Action Failed. " + message, CODE);
	}

	public ActionFailedException(Actional actional) {
		super(actional.toString(), CODE);
	}
}
