package org.yetiz.service.rulengine.core.condition;

import org.yetiz.service.rulengine.core.RuleEngineElement;

/**
 * Created by yeti on 2014/11/28.
 */
public abstract class Condition extends RuleEngineElement implements Judgable {

	private static final long serialVersionUID = 3738124512435905850L;

	public Condition() {
		super();
	}

	public Condition(String name) {
		super(name);
	}

}
