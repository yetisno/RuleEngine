package org.yetiz.service.rulengine.core.helper;

import org.yetiz.service.rulengine.core.Loggable;
import org.yetiz.service.rulengine.core.pattern.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yeti on 14/12/16.
 */
public class PatternUnitHelper extends Loggable {

	private static final long serialVersionUID = -1692307227178620210L;
	private static List<PatternUnit> patternUnits = new ArrayList<PatternUnit>();

	static {
		patternUnits = new ArrayList<PatternUnit>();
		patternUnits.add(new BooleanPatternUnit());
		patternUnits.add(new BigDecimalPatternUnit());
		patternUnits.add(new StringPatternUnit());
		patternUnits.add(new NumberPatternUnit());
		patternUnits.add(new CalendarPatternUnit());
		patternUnits.add(new ObjectPatternUnit());
	}

	/**
	 * Load PatternUnits pairs from <code>registeredPatternUnitList</code> with order. <br />
	 * Default map as follow: <br />
	 * BooleanPatternUnit <---> Boolean <br />
	 * BigDecimalPatternUnit <---> BigDecimal <br />
	 * StringPatternUnit <---> String <br />
	 * NumberPatternUnit <---> Number <br />
	 * CalendarPatternUnit <---> Calendar <br />
	 * ObjectPatternUnit <---> Object <br />
	 *
	 * @param registeredPatternUnitList input PatternUnits pairs
	 */
	public static void loadRegisteredPatternUnitList(List<PatternUnit> registeredPatternUnitList) {
		patternUnits = registeredPatternUnitList;
		for (Iterator<PatternUnit> iterator = patternUnits.iterator(); iterator.hasNext(); ) {
			if (iterator.next() instanceof ObjectPatternUnit) {
				break;
			}
			if (!iterator.hasNext()) {
				patternUnits.add(new ObjectPatternUnit());
				break;
			}
		}
	}

	public static PatternUnit get(String id, Object value) {
		PatternUnit patternUnit;
		for (Iterator<PatternUnit> iterator = patternUnits.iterator(); iterator.hasNext(); ) {
			patternUnit = iterator.next().wrap(id, value);
			if (patternUnit == null)
				continue;
			return patternUnit;
		}
		return null;
	}
}
