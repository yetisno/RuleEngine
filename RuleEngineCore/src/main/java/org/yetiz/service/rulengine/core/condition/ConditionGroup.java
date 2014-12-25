package org.yetiz.service.rulengine.core.condition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeti on 2014/12/1.
 */
public abstract class ConditionGroup extends Condition {

	private static final long serialVersionUID = -7946935669981128999L;
	protected List<Condition> conditions = new ArrayList<Condition>();

	public <T extends ConditionGroup> T add(Condition condition) {
		conditions.add(condition);
		return ((T) this);
	}

	public <T extends ConditionGroup> T remove(int index) {
		conditions.remove(index);
		return ((T) this);
	}

	public List<Condition> getConditions() {
		return conditions;
	}
}
