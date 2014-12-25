package org.yetiz.service.rulengine.core.condition;

import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.pattern.Pattern;

import java.util.Iterator;

/**
 * Created by yeti on 2014/12/1.
 */
public class ANDConditionGroup extends ConditionGroup {


	private static final long serialVersionUID = -2736101365310088757L;

	/**
	 * @see Condition
	 * @see ConditionGroup
	 */
	public Intention judge(Pattern pattern) {
		boolean chainResult = true;
		for (Iterator<Condition> iterator = conditions.iterator(); iterator.hasNext(); ) {
			Condition condition = iterator.next();
			chainResult &= condition.judge(pattern).get();
		}
		return Intention.get(chainResult);
	}

	@Override
	public ANDConditionGroup add(Condition condition) {
		return super.add(condition);
	}

	@Override
	public ANDConditionGroup remove(int index) {
		return super.remove(index);
	}

}
