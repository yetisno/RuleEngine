package org.yetiz.service.rulengine.core.pattern;

import org.yetiz.service.rulengine.core.Loggable;
import org.yetiz.service.rulengine.core.exception.PatternUnitNotExistException;
import org.yetiz.service.rulengine.core.exception.PatternUnitNotMatchedException;
import org.yetiz.service.rulengine.core.helper.PatternUnitHelper;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yeti on 2014/12/5.
 */
public class Pattern extends Loggable {

	private static final long serialVersionUID = -6528750386226671904L;
	private HashMap<String, PatternUnit> units = null;

	public Pattern(Map<String, PatternUnit> units) {
		this.units = new HashMap<String, PatternUnit>(units);
	}

	public Pattern() {
		this.units = new HashMap<String, PatternUnit>();
	}

	private PatternUnit _getPatternUnit(String id) {
		PatternUnit patternUnit = units.get(id);
		if (patternUnit == null) {
			throw new PatternUnitNotExistException();
		}
		return patternUnit;
	}

	public Pattern put(String id, Object value) {
		PatternUnit patternUnit = PatternUnitHelper.get(id, value);
		if (patternUnit == null) {
			throw new PatternUnitNotMatchedException();
		}
		units.put(id, patternUnit);
		return this;
	}

	public Pattern put(PatternUnit patternUnit) {
		units.put(patternUnit.id, patternUnit);
		return this;
	}

	public Boolean contain(String id) {
		return units.get(id) != null;
	}

	public Set<Map.Entry<String, PatternUnit>> entrySet() {
		return units.entrySet();
	}

	public <R extends PatternUnit> R getPatternUnit(String id) {
		return ((R) _getPatternUnit(id));
	}

	public <R> R get(String id) {
		return _getPatternUnit(id).get();
	}

	public <R> R get(String id, Class<R> clazz) {
		return _getPatternUnit(id)._get(clazz);
	}

	public String getString(String id) {
		return get(id);
	}

	public BigDecimal getBigDecimal(String id) {
		return get(id);
	}

	public Number getNumber(String id) {
		return get(id);
	}

	public Calendar getCalendar(String id) {
		return get(id);
	}

	public Boolean getBoolean(String id) {
		return get(id);
	}

	public Integer size() {
		return units.size();
	}

	public void clear(){
		units.clear();
	}
}
