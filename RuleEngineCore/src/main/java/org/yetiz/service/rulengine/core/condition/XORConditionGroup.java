package org.yetiz.service.rulengine.core.condition;

import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.pattern.Pattern;

import java.util.Iterator;

/**
 * Created by yeti on 2014/12/1.
 */
public class XORConditionGroup extends ConditionGroup {


	private static final long serialVersionUID = 2352742552970320793L;

	/**
	 * @see Condition
	 * @see ConditionGroup
	 */
	public Intention judge(Pattern pattern) {
		boolean chainResult = false;
		for (Iterator<Condition> iterator = conditions.iterator(); iterator.hasNext(); ) {
			Condition condition = iterator.next();
			chainResult ^= condition.judge(pattern).get();
		}
		return Intention.get(chainResult);
	}

	@Override
	public XORConditionGroup add(Condition condition) {
		return super.add(condition);
	}

	@Override
	public XORConditionGroup remove(int index) {
		return super.remove(index);
	}

}
