package org.yetiz.service.rulengine.core.exception;

/**
 * Created by yeti on 2014/12/1.
 */
public class FactDuplicatedException extends RuleEngineRuntimeException {
	private static final long serialVersionUID = 6725377061520453679L;
	public static final String CODE = "RE0003";

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 */
	public FactDuplicatedException() {
		super("A Rule CANNOT contain same fact.", CODE);
	}
}
