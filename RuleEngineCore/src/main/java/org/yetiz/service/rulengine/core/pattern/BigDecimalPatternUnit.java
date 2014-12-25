package org.yetiz.service.rulengine.core.pattern;

import java.math.BigDecimal;

/**
 * Created by yeti on 2014/12/5.
 */
public class BigDecimalPatternUnit extends PatternUnit {

	private static final long serialVersionUID = 1971562079326749757L;

	public BigDecimalPatternUnit() {
		super();
	}

	public BigDecimalPatternUnit(String id, BigDecimal value) {
		super(id, value);
	}

	/**
	 * Get value with <code>BigDecimal</code> type.
	 *
	 * @return value.
	 * @see java.math.BigDecimal
	 * @see PatternUnit
	 */
	@Override
	public BigDecimal get() {
		return _get(BigDecimal.class);
	}

	/**
	 * set the value
	 *
	 * @param value the <code>value</code> of <code>PatternUnit</code>
	 * @return the instance of self.
	 */
	@Override
	public BigDecimalPatternUnit set(Object value) {
		return super._set(BigDecimal.class, value);
	}

	/**
	 * wrap value to <code>BigDecimalPatternUnit</code> type.
	 *
	 * @param id    id of <code>PatternUnit</code>
	 * @param value value of <code>PatternUnit</code>
	 * @return the mapped type of value, otherwise, return null.
	 */
	@Override
	public BigDecimalPatternUnit wrap(String id, Object value) {
		if (value instanceof BigDecimal) {
			return new BigDecimalPatternUnit(id, ((BigDecimal) value));
		}
		return null;
	}
}
