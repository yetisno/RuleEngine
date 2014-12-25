package org.yetiz.service.rulengine.core.fact;

import org.yetiz.service.rulengine.core.Actional;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.RuleEngineElement;
import org.yetiz.service.rulengine.core.condition.Condition;
import org.yetiz.service.rulengine.core.exception.ActionFailedException;
import org.yetiz.service.rulengine.core.exception.FactNotEvaluatedException;
import org.yetiz.service.rulengine.core.pattern.Pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yeti on 2014/11/28.
 */
public class ActionFact extends Fact implements FactTailal, Actional {

	private static final long serialVersionUID = 1601351386303157732L;
	private List<Actional> positiveActions = new ArrayList<Actional>();
	private List<Actional> negativeActions = new ArrayList<Actional>();
	private Fact positiveTailFact = null;
	private Fact negativeTailFact = null;
	private String LINE_SEPARATOR = System.getProperty("line.separator");

	public ActionFact() {
		super();
	}

	public ActionFact(String name) {
		super(name);
	}

	public ActionFact(String name, Condition condition) {
		super(name);
		setCondition(condition);
	}

	public ActionFact(String name, Condition condition, Actional positiveAction, Actional negativeAction) {
		super(name);
		setCondition(condition);
		addAction(Intention.POSITIVE, positiveAction);
		addAction(Intention.NEGATIVE, negativeAction);
	}

	@Override
	public ActionFact setCondition(Condition condition) {
		intention = null;
		return super.setCondition(condition);
	}

	public ActionFact setTail(Intention intention, Fact fact) {
		if (intention == Intention.POSITIVE) {
			this.positiveTailFact = fact;
		} else {
			this.negativeTailFact = fact;
		}
		return this;
	}

	public Fact getTail(Intention intention) {
		if (intention == Intention.POSITIVE) {
			return this.positiveTailFact;
		} else {
			return this.negativeTailFact;
		}
	}

	public ActionFact addAction(Intention intention, Actional actional) {
		if (intention == Intention.POSITIVE) {
			this.positiveActions.add(actional);
		} else {
			this.negativeActions.add(actional);
		}
		return this;
	}

	/**
	 * @see org.yetiz.service.rulengine.core.fact.Fact
	 */
	@Override
	public Intention getIntention() {
		if (intention != null) {
			return intention;
		}
		throw new FactNotEvaluatedException();
	}

	public ActionFact doAction(Pattern pattern) {
		Actional currentAction = null;
		try {
			List<Actional> actions = null;
			if (intention == Intention.POSITIVE) {
				if (positiveActions.size() > 0) {
					actions = positiveActions;
				}
			} else {
				if (negativeActions.size() > 0) {
					actions = negativeActions;
				}
			}
			if (actions != null) {
				for (Iterator<Actional> iterator = actions.iterator(); iterator.hasNext(); ) {
					currentAction = iterator.next().doAction(pattern);
				}
			}
		} catch (Exception e) {
			StringBuilder rtn = new StringBuilder();
			if (currentAction instanceof RuleEngineElement) {
				rtn.append(((RuleEngineElement) currentAction).BASE.toString() + LINE_SEPARATOR);
			}
			rtn.append(e.getMessage());
			throw new ActionFailedException(rtn.toString());
		}
		return this;
	}

	public ActionFact doTail(Pattern pattern) {
		if (intention == Intention.POSITIVE) {
			if (positiveTailFact != null)
				positiveTailFact.evaluate(pattern);
		} else {
			if (negativeTailFact != null)
				negativeTailFact.evaluate(pattern);
		}
		return this;
	}

	public ActionFact clearActions(Intention intention) {
		if (intention == Intention.POSITIVE) {
			positiveActions.clear();
		} else {
			negativeActions.clear();
		}
		return this;
	}
}
