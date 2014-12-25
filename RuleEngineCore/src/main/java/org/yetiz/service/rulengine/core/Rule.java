package org.yetiz.service.rulengine.core;

import org.yetiz.service.rulengine.core.exception.FactDuplicatedException;
import org.yetiz.service.rulengine.core.fact.Fact;
import org.yetiz.service.rulengine.core.pattern.Pattern;
import org.yetiz.service.rulengine.core.validation.ShallowFactCycleValidator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yeti on 2014/11/28.
 */
public class Rule extends RuleEngineElement implements Evaluatable {
	private static final long serialVersionUID = 1833866547752767974L;
	private ArrayList<Fact> facts = new ArrayList<Fact>();

	public Rule() {
		super();
	}

	public Rule(String name) {
		super(name);
	}

	public Rule evaluate(Pattern pattern) {
		for (Iterator<Fact> iterator = facts.iterator(); iterator.hasNext(); ) {
			iterator.next().evaluate(pattern);
		}
		return this;
	}

	public Fact getFact(String factID) {
		for (Iterator<Fact> iterator = facts.iterator(); iterator.hasNext(); ) {
			Fact fact = iterator.next();
			if (fact.BASE.getID().equals(factID))
				return fact;
		}
		return null;
	}

	public Fact getFact(int index) {
		return facts.get(index);
	}

	public int getFactSize() {
		return facts.size();
	}

	public List<Fact> getFacts() {
		return facts;
	}

	public Rule addFact(Fact fact) {
		if (!facts.contains(fact)) {
			facts.add(fact);
		} else {
			throw new FactDuplicatedException();
		}
		return this;
	}

	public Rule addFact(int index, Fact fact) {
		if (!facts.contains(fact)) {
			facts.add(index, fact);
		} else {
			throw new FactDuplicatedException();
		}
		return this;
	}

	public Rule removeFact(Fact fact) {
		facts.remove(fact);
		return this;
	}

	public Rule removeFact(int index) {
		facts.remove(index);
		return this;
	}

	public Rule removeFact(String factID) {
		for (int i = facts.size() - 1; i > -1; i--) {
			if (facts.get(i).BASE.getID().equals(factID)) {
				facts.remove(i);
			}
		}
		return this;
	}

	public boolean hasPotentialCycle() {
		for (Iterator<Fact> iterator = facts.iterator(); iterator.hasNext(); ) {
			if (!new ShallowFactCycleValidator().validate(iterator.next()))
				return false;
		}
		return true;
	}

	public Rule clearFact() {
		facts.clear();
		return this;
	}

}
