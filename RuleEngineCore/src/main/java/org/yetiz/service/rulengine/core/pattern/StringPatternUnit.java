package org.yetiz.service.rulengine.core.pattern;

/**
 * Created by yeti on 2014/12/5.
 */
public class StringPatternUnit extends PatternUnit {

	private static final long serialVersionUID = 8769526821409866219L;

	public StringPatternUnit() {
		super();
	}

	public StringPatternUnit(String id, String value) {
		super(id, value);
	}

	/**
	 * Get value with <code>String</code> type.
	 *
	 * @return value.
	 * @see java.lang.String
	 * @see PatternUnit
	 */
	@Override
	public String get() {
		return _get(String.class);
	}

	/**
	 * set the value
	 *
	 * @param value the <code>value</code> of <code>PatternUnit</code>
	 * @return the instance of self.
	 */
	@Override
	public StringPatternUnit set(Object value) {
		return super.set(value);
	}

	/**
	 * wrap value to <code>StringPatternUnit</code> type.
	 *
	 * @param id    id of <code>PatternUnit</code>
	 * @param value value of <code>PatternUnit</code>
	 * @return the mapped type of value, otherwise, return null.
	 */
	@Override
	public StringPatternUnit wrap(String id, Object value) {
		if (value instanceof String) {
			return new StringPatternUnit(id, ((String) value));
		}
		return null;
	}
}
