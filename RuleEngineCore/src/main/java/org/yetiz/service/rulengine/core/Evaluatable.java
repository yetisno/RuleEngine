package org.yetiz.service.rulengine.core;

import org.yetiz.service.rulengine.core.pattern.Pattern;

/**
 * Created by yeti on 14/12/11.
 */
public interface Evaluatable {
	/**
	 * <code>evaluate</code> the pattern and return callee.
	 *
	 * @param pattern the pattern be evaluated.
	 * @return the instance of callee.
	 */
	<R extends Evaluatable> R evaluate(Pattern pattern);
}
