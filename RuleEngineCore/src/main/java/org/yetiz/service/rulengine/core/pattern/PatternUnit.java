package org.yetiz.service.rulengine.core.pattern;

import org.yetiz.service.rulengine.core.Loggable;
import org.yetiz.service.rulengine.core.exception.ParseFormatErrorException;
import org.yetiz.service.rulengine.core.exception.PatternUnitValueTypeErrorException;

import java.util.UUID;

/**
 * Created by yeti on 2014/12/5.
 */
public abstract class PatternUnit extends Loggable {

	private static final long serialVersionUID = 7455761125186525607L;
	protected String id = null;
	private Class clazz = null;
	private Object value = null;

	protected PatternUnit() {
		id = UUID.randomUUID().toString();
	}

	protected PatternUnit(String id, Object value) {
		this.id = id;
		set(value);
	}

	/**
	 * @param <R>
	 * @return
	 * @throws org.yetiz.service.rulengine.core.exception.ParseFormatErrorException
	 */
	protected <R> R _get(Class<R> clazz) {
		try {
			return clazz.cast(value);
		} catch (Exception e) {
			throw new ParseFormatErrorException("id=" + BASE.getID() + ", name=" + BASE.getName());
		}
	}

	/**
	 * Get the <code>value</code> of <code>PatternUnit</code>.
	 *
	 * @return the value.
	 */
	public abstract <R> R get();

	protected <R extends PatternUnit, T> R _set(Class<T> clazz, Object value) {
		if (!clazz.isAssignableFrom(value.getClass())) {
			throw new PatternUnitValueTypeErrorException(value);
		}
		this.value = value;
		this.clazz = value.getClass();
		return ((R) this);
	}

	/**
	 * set the value
	 *
	 * @param value the <code>value</code> of <code>PatternUnit</code>
	 * @param <R>   the Type of Class extend <code>PatternUnital</code>.
	 * @return the instance of self.
	 */
	public <R extends PatternUnit> R set(Object value) {
		return _set(Object.class, value);
	}

	/**
	 * Get the <code>Class</code> of <code>value</code>.
	 *
	 * @return the value class.
	 */
	public Class getValueClass() {
		return clazz;
	}

	/**
	 * wrap value to suitable type.
	 *
	 * @param id    id of <code>PatternUnit</code>
	 * @param value value of <code>PatternUnit</code>
	 * @param <R>   mapped type of value.
	 * @return the mapped type of value, otherwise, return null.
	 */
	public abstract <R extends PatternUnit> R wrap(String id, Object value);
}
