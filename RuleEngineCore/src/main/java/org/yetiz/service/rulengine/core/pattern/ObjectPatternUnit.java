package org.yetiz.service.rulengine.core.pattern;

/**
 * Created by yeti on 2014/12/5.
 */
public class ObjectPatternUnit extends PatternUnit {

	private static final long serialVersionUID = 6729622245368956596L;

	public ObjectPatternUnit() {
		super();
	}

	public ObjectPatternUnit(String id, Object value) {
		super(id, value);
	}

	/**
	 * Get value with <code>Object</code> type.
	 *
	 * @return value.
	 * @see java.lang.Object
	 * @see PatternUnit
	 */
	public Object get() {
		return _get(Object.class);
	}

	/**
	 * wrap value to <code>ObjectPatternUnit</code> type.
	 *
	 * @param id    id of <code>ObjectPatternUnit</code>
	 * @param value value of <code>ObjectPatternUnit</code>
	 * @return the mapped type of value, otherwise, return null.
	 */
	@Override
	public ObjectPatternUnit wrap(String id, Object value) {
		if (value == null)
			return null;
		return new ObjectPatternUnit(id, value);
	}
}
