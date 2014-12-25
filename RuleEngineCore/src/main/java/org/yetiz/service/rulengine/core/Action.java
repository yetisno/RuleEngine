package org.yetiz.service.rulengine.core;

import org.yetiz.service.rulengine.core.pattern.Pattern;

/**
 * Created by yeti on 2014/12/1.
 */
public abstract class Action extends RuleEngineElement implements Actional {

	private static final long serialVersionUID = -1795478651930504332L;

	/**
	 * implement this to do anything you want to do.
	 *
	 * @param pattern the input <code>Pattern</code>
	 */
	public abstract Action doAction(Pattern pattern);
}
