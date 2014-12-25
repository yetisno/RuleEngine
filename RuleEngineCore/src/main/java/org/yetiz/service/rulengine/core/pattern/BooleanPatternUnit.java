/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.yetiz.service.rulengine.core.pattern;

/**
 * Created by yeti on 2014/12/5.
 */
public class BooleanPatternUnit extends PatternUnit {

	private static final long serialVersionUID = -4290690983919810072L;

	public BooleanPatternUnit() {
		super();
	}

	public BooleanPatternUnit(String id, Boolean value) {
		super(id, value);
	}

	/**
	 * Get value with <code>String</code> type.
	 *
	 * @return value.
	 * @see String
	 * @see org.yetiz.service.rulengine.core.pattern.PatternUnit
	 */
	@Override
	public Boolean get() {
		return _get(Boolean.class);
	}

	/**
	 * set the value
	 *
	 * @param value the <code>value</code> of <code>PatternUnit</code>
	 * @return the instance of self.
	 */
	@Override
	public BooleanPatternUnit set(Object value) {
		return super._set(Boolean.class, value);
	}

	/**
	 * wrap value to <code>StringPatternUnit</code> type.
	 *
	 * @param id    id of <code>PatternUnit</code>
	 * @param value value of <code>PatternUnit</code>
	 * @return the mapped type of value, otherwise, return null.
	 */
	@Override
	public BooleanPatternUnit wrap(String id, Object value) {
		if (value instanceof Boolean) {
			return new BooleanPatternUnit(id, ((Boolean) value));
		}
		return null;
	}
}
