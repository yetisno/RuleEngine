package org.yetiz.service.rulengine.core.fact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.condition.ANDConditionGroup;
import org.yetiz.service.rulengine.core.condition.Condition;
import org.yetiz.service.rulengine.core.exception.ConditionJudgeFailedException;
import org.yetiz.service.rulengine.core.exception.RuleEngineRuntimeException;
import org.yetiz.service.rulengine.core.pattern.Pattern;

public class FactTest {

	Fact fact;

	@Before
	public void setUp() throws Exception {
		fact = new ActionFact("NAME");
		fact.setCondition(new Condition() {
			public Intention judge(Pattern pattern) {
				return Intention.POSITIVE;
			}
		});
	}

	@Test
	public void testSetCondition() throws Exception {
		fact.setCondition(new Condition() {
			public Intention judge(Pattern pattern) {
				return Intention.POSITIVE;
			}
		});
		fact.evaluate(null);
		Assert.assertEquals(Intention.POSITIVE, fact.getIntention());
	}

	@Test
	public void testGetIntention() throws Exception {
		fact.setCondition(new Condition() {
			public Intention judge(Pattern pattern) {
				return Intention.POSITIVE;
			}
		});
		fact.evaluate(null);
		Assert.assertEquals(Intention.POSITIVE, fact.getIntention());
		fact.setCondition(new ANDConditionGroup().add(
			new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.NEGATIVE;
				}
			}
		).add(new Condition() {
			public Intention judge(Pattern pattern) {
				return Intention.POSITIVE;
			}
		}));
		fact.evaluate(null);
		Assert.assertNotEquals(Intention.POSITIVE, fact.getIntention());
	}

	@Test
	public void testPreEvaluate() throws Exception {
		Assert.assertEquals(fact, fact.preEvaluate(null));
	}

	@Test
	public void testEvaluate() throws Exception {
		Assert.assertEquals(fact, fact.evaluate(null));
	}

	@Test(expected = ConditionJudgeFailedException.class)
	public void testEvaluating() throws Exception {
		Assert.assertEquals(fact, fact.evaluating(null));
		fact.setCondition(new Condition() {
			public Intention judge(Pattern pattern) {
				throw new RuleEngineRuntimeException("SS", "C");
			}
		}).evaluating(null);
	}

	@Test
	public void testPostEvaluate() throws Exception {
		Assert.assertEquals(fact, fact.postEvaluate(null));
	}

	@Test
	public void testClone() throws Exception {
		Fact fact1 = ((Fact) fact.clone());
		Assert.assertEquals(fact1.BASE.getName(), fact.BASE.getName());
		Assert.assertNotEquals(fact1.BASE.getID(), fact.BASE.getID());
	}
}