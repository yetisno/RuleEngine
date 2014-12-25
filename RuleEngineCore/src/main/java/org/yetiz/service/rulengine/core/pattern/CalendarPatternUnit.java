package org.yetiz.service.rulengine.core.pattern;

import java.util.Calendar;

/**
 * Created by yeti on 2014/12/5.
 */
public class CalendarPatternUnit extends PatternUnit {

	private static final long serialVersionUID = -1736934226742145984L;

	public CalendarPatternUnit() {
		super();
	}

	public CalendarPatternUnit(String id, Calendar value) {
		super(id, value);
	}

	/**
	 * Get value with <code>Calendar</code> type.
	 *
	 * @return value.
	 * @see java.util.Calendar
	 * @see PatternUnit
	 */
	@Override
	public Calendar get() {
		return _get(Calendar.class);
	}

	/**
	 * set the value
	 *
	 * @param value the <code>value</code> of <code>PatternUnit</code>
	 * @return the instance of self.
	 */
	@Override
	public CalendarPatternUnit set(Object value) {
		return super._set(Calendar.class, value);
	}

	/**
	 * wrap value to <code>CalendarPatternUnit</code> type.
	 *
	 * @param id    id of <code>PatternUnit</code>
	 * @param value value of <code>PatternUnit</code>
	 * @return the mapped type of value, otherwise, return null.
	 */
	@Override
	public CalendarPatternUnit wrap(String id, Object value) {
		if (value instanceof Calendar) {
			return new CalendarPatternUnit(id, ((Calendar) value));
		}
		return null;
	}
}
