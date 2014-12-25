package org.yetiz.service.rulengine.core.exception;

/**
 * Created by yeti on 2014/12/1.
 */
public class FactNotEvaluatedException extends RuleEngineRuntimeException {
	private static final long serialVersionUID = -7087248890637067745L;
	public static final String CODE = "RE0006";

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 */
	public FactNotEvaluatedException() {
		super("Fact is NOT evaluated, no intention.", CODE);
	}
}
