package org.yetiz.service.rulengine.core.fact;

import org.yetiz.service.rulengine.core.Evaluatable;
import org.yetiz.service.rulengine.core.pattern.Pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yeti on 14/12/12.
 */
public abstract class FactGroup extends Fact {

	private static final long serialVersionUID = 6447233081416481426L;
	protected List<Fact> facts = new ArrayList<Fact>();
	protected boolean evaluated = false;

	/**
	 * this method will evaluate all Facts in group and set <code>evaluated</code> to <code>true</code>.
	 *
	 * @param pattern the input pattern.
	 * @return the Instance of Callee.
	 * @see org.yetiz.service.rulengine.core.Evaluatable
	 */
	public FactGroup evaluating(Pattern pattern) {
		for (Iterator<Fact> iterator = facts.iterator(); iterator.hasNext(); ) {
			iterator.next().evaluate(pattern);
		}
		evaluated = true;
		return this;
	}


	public <T extends FactGroup> T add(Fact fact) {
		evaluated = false;
		facts.add(fact);
		return ((T) this);
	}

	public <T extends FactGroup> T remove(int index) {
		evaluated = false;
		facts.remove(index);
		return ((T) this);
	}

	public List<Fact> getFacts() {
		return facts;
	}
}
