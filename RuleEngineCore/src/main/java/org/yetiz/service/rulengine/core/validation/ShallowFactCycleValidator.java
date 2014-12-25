package org.yetiz.service.rulengine.core.validation;

import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.fact.Fact;
import org.yetiz.service.rulengine.core.fact.FactTailal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeti on 2014/12/15.
 */
public class ShallowFactCycleValidator implements Validatable {

	public Boolean validate(Object fact) {
		Fact currentFact = ((Fact) fact);
		List<Fact> factStack = new ArrayList<Fact>();
		return determine(factStack, currentFact, 0);
	}

	private Boolean determine(List<Fact> factStack, Fact currentFact, int count) {
		if (count > 1000){
			return false;
		}
		count++;
		if (currentFact instanceof FactTailal) {
			if (!_determine(factStack, currentFact, count, Intention.POSITIVE)) {
				return false;
			}
			if (!_determine(factStack, currentFact, count, Intention.NEGATIVE)) {
				return false;
			}
		}
		return true;
	}

	private Boolean _determine(List<Fact> factStack, Fact currentFact, int count, Intention intention) {
		Fact fact = ((FactTailal) currentFact).getTail(intention);
		if (fact == null) {
			return true;
		}
		if (factStack.contains(fact)) {
			return false;
		}
		factStack.add(fact);
		if (!determine(factStack, fact, count)) {
			return false;
		}
		factStack.remove(fact);
		return true;
	}


}
