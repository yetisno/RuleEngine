package org.yetiz.service.rulengine.core;

/**
 * Created by yeti on 2014/12/15.
 */
public enum Intention {
	POSITIVE(Boolean.TRUE),
	NEGATIVE(Boolean.FALSE);

	private Boolean intention;

	Intention(Boolean value) {
		intention = value;
	}

	public static Intention get(Boolean value) {
		return value ? POSITIVE : NEGATIVE;
	}

	public Boolean get() {
		return intention;
	}
}
