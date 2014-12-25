package org.yetiz.service.rulengine.core.pattern;

/**
 * Created by yeti on 2014/12/5.
 */
public class NumberPatternUnit extends PatternUnit {

	private static final long serialVersionUID = 6640105320872626771L;

	public NumberPatternUnit() {
		super();
	}

	public NumberPatternUnit(String id, Number value) {
		super(id, value);
	}

	/**
	 * Get value with <code>Number</code> type.
	 *
	 * @return value.
	 * @see java.lang.Number
	 * @see PatternUnit
	 */
	@Override
	public Number get() {
		return _get(Number.class);
	}

	/**
	 * set the value
	 *
	 * @param value the <code>value</code> of <code>PatternUnit</code>
	 * @return the instance of self.
	 */
	@Override
	public NumberPatternUnit set(Object value) {
		return super._set(Number.class, value);
	}

	/**
	 * wrap value to <code>NumberPatternUnit</code> type.
	 *
	 * @param id    id of <code>PatternUnit</code>
	 * @param value value of <code>PatternUnit</code>
	 * @return the mapped type of value, otherwise, return null.
	 */
	@Override
	public NumberPatternUnit wrap(String id, Object value) {
		if (value instanceof Number) {
			return new NumberPatternUnit(id, ((Number) value));
		}
		return null;
	}
}
