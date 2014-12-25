package org.yetiz.service.rulengine.core.fact;

import org.yetiz.service.rulengine.core.Actional;
import org.yetiz.service.rulengine.core.Evaluatable;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.RuleEngineElement;
import org.yetiz.service.rulengine.core.condition.Condition;
import org.yetiz.service.rulengine.core.exception.ConditionJudgeFailedException;
import org.yetiz.service.rulengine.core.pattern.Pattern;

/**
 * Created by yeti on 2014/11/28.
 */
public abstract class Fact extends RuleEngineElement implements Evaluatable {
	private static final long serialVersionUID = -6147855662904682250L;
	protected Intention intention = null;
	private Condition condition = null;

	public Fact() {
		super();
	}

	/**
	 * Create a Fact instance, and set the name of this <code>Fact</code>.
	 *
	 * @param name the name of fact.
	 */
	public Fact(String name) {
		super(name);
	}

	public <R extends Fact> R setCondition(Condition condition) {
		this.condition = condition;
		return ((R) this);
	}

	/**
	 * Get the intention of this <code>Fact</code>.
	 * Attention!!! This method MUST only can be invoke after <code>evaluate()</code> is called.
	 *
	 * @return the intention of this <code>Fact</code>.
	 */
	public abstract Intention getIntention();

	public <R extends Fact> R preEvaluate(Pattern pattern) {
		return ((R) this);
	}

	/**
	 * <code>evaluate</code> the pattern and return callee.
	 * function <code>evaluate</code> will run those functions, <code>preEvaluate</code>,
	 * <code>evaluating</code> and <code>postEvaluate</code>.
	 * @param pattern the pattern be evaluated.
	 * @return the instance of callee.
	 */
	public Fact evaluate(Pattern pattern) {
		return
			preEvaluate(pattern).
				evaluating(pattern).
				postEvaluate(pattern);
	}

	public <R extends Fact> R evaluating(Pattern pattern) {
		if (condition != null)
			try {
				intention = condition.judge(pattern);
			} catch (Throwable t) {
				throw new ConditionJudgeFailedException(t.toString());
			}
		return ((R) this);
	}

	public <R extends Fact> R postEvaluate(Pattern pattern) {
		if (this instanceof Actional) {
			((Actional) this).doAction(pattern);
		}
		if (this instanceof FactTailal) {
			((FactTailal) this).doTail(pattern);
		}
		return ((R) this);
	}

}
