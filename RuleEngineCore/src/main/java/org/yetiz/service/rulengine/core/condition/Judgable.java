package org.yetiz.service.rulengine.core.condition;

import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.pattern.Pattern;

/**
 * Created by yeti on 14/12/11.
 */
public interface Judgable {

	/**
	 * <code>judge</code> this condition with pattern.
	 *
	 * @return the judge result.
	 */
	Intention judge(Pattern pattern);
}
