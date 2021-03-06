package org.yetiz.service.rulengine.core.fact;

import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.exception.FactNotEvaluatedException;

import java.util.Iterator;

/**
 * Created by yeti on 14/12/12.
 */
public class AndFactGroup extends FactGroup {


	private static final long serialVersionUID = -8492797836598912466L;

	/**
	 * Get the intention of this <code>Fact</code>.
	 * Attention!!! This method MUST only can be invoke after <code>evaluate()</code> is called.
	 *
	 * @return the intention of this <code>Fact</code>.
	 * @see org.yetiz.service.rulengine.core.fact.Fact
	 */
	@Override
	public Intention getIntention() {
		if (!evaluated)
			throw new FactNotEvaluatedException();
		boolean intention = true;
		for (Iterator<Fact> iterator = facts.iterator(); iterator.hasNext(); ) {
			intention &= iterator.next().getIntention().get();
		}
		return Intention.get(intention);
	}
}
