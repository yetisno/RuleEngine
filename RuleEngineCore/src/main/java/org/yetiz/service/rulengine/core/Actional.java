package org.yetiz.service.rulengine.core;

import org.yetiz.service.rulengine.core.pattern.Pattern;

/**
 * Created by yeti on 2014/12/11.
 */
public interface Actional {

	/**
	 * implement this to do anything you want to do.
	 *
	 * @param pattern the input <code>Pattern</code>
	 */
	Actional doAction(Pattern pattern);
}
