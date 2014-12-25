package org.yetiz.service.rulengine.core;

import java.io.Serializable;

/**
 * Created by yeti on 2014/12/1.
 */
public abstract class RuleEngineElement extends Loggable implements Serializable {

	private static final long serialVersionUID = 2198502281204853230L;

	public RuleEngineElement() {
		super();
	}

	public RuleEngineElement(String name) {
		super(name);
	}

}
