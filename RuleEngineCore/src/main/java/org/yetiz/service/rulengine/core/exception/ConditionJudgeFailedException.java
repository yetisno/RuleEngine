package org.yetiz.service.rulengine.core.exception;

/**
 * Created by yeti on 2014/12/1.
 */
public class ConditionJudgeFailedException extends RuleEngineRuntimeException {
	private static final long serialVersionUID = -8296794932374516088L;
	public static final String CODE = "RE0004";

	public ConditionJudgeFailedException() {
		super("condition judge fail.", CODE);
	}

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public ConditionJudgeFailedException(String message) {
		super("condition judge fail. " + message, CODE);
	}
}
