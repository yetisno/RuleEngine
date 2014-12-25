package org.yetiz.service.rulengine.core.fact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Action;
import org.yetiz.service.rulengine.core.Actional;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.condition.Condition;
import org.yetiz.service.rulengine.core.exception.ActionFailedException;
import org.yetiz.service.rulengine.core.exception.ConditionJudgeFailedException;
import org.yetiz.service.rulengine.core.exception.FactNotEvaluatedException;
import org.yetiz.service.rulengine.core.pattern.Pattern;

public class ActionFactTest {

	ActionFact actionFact;

	@Before
	public void setUp() throws Exception {
		actionFact = new ActionFact();
		actionFact = new ActionFact("Fact");
		actionFact = new ActionFact("Fact", new Condition() {
			public Intention judge(Pattern pattern) {
				return null;
			}
		});
		actionFact = new ActionFact("Fact", new Condition() {
			public Intention judge(Pattern pattern) {
				return null;
			}
		}, new Action() {
			@Override
			public Action doAction(Pattern pattern) {
				return null;
			}
		}, new Action() {
			@Override
			public Action doAction(Pattern pattern) {
				return null;
			}
		});
		actionFact = new ActionFact();

	}

	@Test(expected = FactNotEvaluatedException.class)
	public void testSetCondition() throws Exception {
		actionFact.setCondition(new Condition() {
			public Intention judge(Pattern pattern) {
				return Intention.POSITIVE;
			}
		}).evaluate(null);
		Assert.assertEquals(Intention.POSITIVE, actionFact.getIntention());
		actionFact.setCondition(new Condition() {
			public Intention judge(Pattern pattern) {
				return Intention.POSITIVE;
			}
		});
		Assert.assertEquals(Intention.POSITIVE, actionFact.getIntention());
	}

	@Test
	public void testSetTail() throws Exception {
		Assert.assertEquals(Intention.POSITIVE,
			((ActionFact) actionFact.setTail(Intention.POSITIVE, new Fact() {
				@Override
				public Intention getIntention() {
					return Intention.POSITIVE;
				}
			}).setTail(Intention.NEGATIVE, new Fact() {
				@Override
				public Intention getIntention() {
					return Intention.NEGATIVE;
				}
			})
				.setCondition(new Condition() {
					public Intention judge(Pattern pattern) {
						return Intention.POSITIVE;
					}
				})
				.evaluate(null))
				.getTail(Intention.POSITIVE)
				.getIntention());
		Assert.assertEquals(Intention.NEGATIVE, actionFact.getTail(Intention.NEGATIVE).getIntention());
	}

	@Test
	public void testGetTail() throws Exception {
		Fact fact = new Fact() {
			@Override
			public Intention getIntention() {
				return null;
			}
		};
		actionFact.setTail(Intention.POSITIVE, fact);
		Assert.assertEquals(fact, actionFact.getTail(Intention.POSITIVE));
		Assert.assertNull(actionFact.getTail(Intention.NEGATIVE));
	}

	@Test
	public void testAddAction() throws Exception {
		Pattern pattern = new Pattern();
		actionFact
			.setCondition(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			})
			.addAction(Intention.POSITIVE, new Actional() {
				public Actional doAction(Pattern pattern) {
					pattern.put("GG", "GGG");
					return null;
				}
			})
			.addAction(Intention.NEGATIVE, new Action() {
				@Override
				public Action doAction(Pattern pattern) {
					Assert.fail();
					return null;
				}
			})
			.evaluate(pattern);
		Assert.assertEquals("GGG", pattern.getString("GG"));
	}

	@Test
	public void testGetIntention() throws Exception {
		actionFact.setCondition(new Condition() {
			public Intention judge(Pattern pattern) {
				return Intention.POSITIVE;
			}
		}).evaluate(null);
		Assert.assertEquals(Intention.POSITIVE, actionFact.getIntention());
	}

	@Test(expected = ActionFailedException.class)
	public void testDoAction() throws Exception {
		actionFact
			.addAction(Intention.POSITIVE, new Actional() {
				public Actional doAction(Pattern pattern) {
					throw new ActionFailedException("TEST");
				}
			})
			.setCondition(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			})
			.evaluate(null);
	}

	@Test(expected = ConditionJudgeFailedException.class)
	public void testDoTail() throws Exception {
		actionFact
			.setCondition(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			})
			.setTail(Intention.POSITIVE, new ActionFact()
				.setCondition(new Condition() {
					public Intention judge(Pattern pattern) {
						throw new ConditionJudgeFailedException();
					}
				}))
			.evaluate(null);
	}

	@Test
	public void testClearActions() throws Exception {
		actionFact
			.addAction(Intention.POSITIVE, new Actional() {
				public Actional doAction(Pattern pattern) {
					throw new ActionFailedException("THS");
				}
			})
			.setCondition(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			})
			.clearActions(Intention.POSITIVE)
			.evaluate(null);
	}
}