/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.yetiz.service.rulengine.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.condition.ANDConditionGroup;
import org.yetiz.service.rulengine.core.condition.Condition;
import org.yetiz.service.rulengine.core.fact.ActionFact;
import org.yetiz.service.rulengine.core.pattern.Pattern;

import java.math.BigDecimal;

public class RuleEngineUseCaseTest {
	Rule emptyRule, namedRule;
	ActionFact emptyActionFact, namedActionFact, trueConditionActionFact, falseConditionActionFact, actionActionFact;
	Actional safeAction, failAction, truePatternAction;
	Condition trueCondition, falseCondition;
	Pattern pattern;

	@Before
	public void setUp() throws Exception {
		trueCondition = new Condition() {
			public Intention judge(Pattern pattern) {
				return Intention.get(true);
			}
		};
		falseCondition = new Condition() {
			public Intention judge(Pattern pattern) {
				return Intention.get(false);
			}
		};
		safeAction = new Action() {
			public Action doAction(Pattern pattern) {
				return this;
			}
		};
		failAction = new Action() {
			@Override
			public Action doAction(Pattern pattern) {
				Assert.fail();
				return this;
			}
		};
		truePatternAction = new Action() {
			@Override
			public Action doAction(Pattern pattern) {
				pattern.put("L", new Long(20));
				return this;
			}
		};
		emptyRule = new Rule();
		namedRule = new Rule("namedRule");
		emptyActionFact = new ActionFact();
		namedActionFact = new ActionFact("namedActionFact");
		trueConditionActionFact = new ActionFact("trueConditionActionFact", trueCondition);
		falseConditionActionFact = new ActionFact("falseConditionActionFact", falseCondition);
		actionActionFact = new ActionFact("actionActionFact", trueCondition, safeAction, failAction);
		pattern = new Pattern();
		pattern.put("T", true);
		pattern.put("F", false);
		pattern.put("S", "String");
		pattern.put("B", new BigDecimal("12"));
		pattern.put("L", 10l);
		pattern.put("A", safeAction);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test(expected = java.lang.AssertionError.class)
	public void testRound1() throws Exception {
		Rule rule = emptyRule;
		rule.clearFact();
		emptyActionFact.setCondition(trueCondition);
		Assert.assertTrue(rule.addFact(emptyActionFact).evaluate(pattern).getFact(0).getIntention().get());
		Assert.assertEquals("namedActionFact", namedActionFact.BASE.getName());
		namedActionFact
			.setCondition(trueCondition)
			.addAction(Intention.POSITIVE, truePatternAction)
			.addAction(Intention.NEGATIVE, failAction);
		rule.addFact(namedActionFact).evaluate(pattern);
		Assert.assertEquals(20l, pattern.getNumber("L"));
		rule.clearFact();
		trueConditionActionFact.addAction(Intention.POSITIVE, safeAction);
		rule.addFact(trueConditionActionFact).evaluate(pattern);
		Assert.assertEquals(1, rule.getFactSize());
		rule.addFact(actionActionFact);
		rule.evaluate(pattern);
		Assert.assertTrue(rule.getFact(0).getIntention().get());
		Assert.assertEquals(2, rule.getFactSize());
		Assert.assertEquals("trueConditionActionFact", rule.getFact(0).BASE.getName());
		Assert.assertEquals("actionActionFact", rule.getFact(1).BASE.getName());
		((ActionFact) rule.getFact(1)).clearActions(Intention.POSITIVE).addAction(Intention.POSITIVE, failAction);
		rule.evaluate(pattern);
	}

	@Test
	public void testRound2() throws Exception {
		/**
		 * Create a rule
		 * which use to determine whether the loan is accepted or not
		 * and evaluate the Level by Budget.
		 *
		 * Fact 1(Phase1).
		 * 
		 *  Condition:
		 *      there two conditions in a AndConditionGroup(Means it's POSITIVE when all conditions in this group are
		 *      POSITIVE)
		 *      AndConditionGroup:
		 *          Condition 1.
		 *              POSITIVE when Money > 1000, or NEGATIVE.
		 *          Condition 2.
		 *              POSITIVE when Age > 30, or NEGATIVE.
		 *          
		 *  Action:
		 *      null.
		 *  
		 *  Tail:
		 *      POSITIVE -> evaluate Fact 1-1(Phase1-1)
		 *      NEGATIVE -> evaluate Fact 1-2(Phase1-2)
		 *
		 * Fact 1-1(Phase1-1).
		 *  Condition:
		 *      POSITIVE when hasBicycle is true, or NEGATIVE.
		 *
		 *  Action:
		 *      POSITIVE -> pattern put Result as true
		 *      NEGATIVE -> pattern put Result as false
		 *               -> pattern put MEMO as Have no Bicycle
		 *  Tail:
		 *      null
		 *
		 * Fact 1-2(Phase1-2).
		 *  Condition:
		 *      POSITIVE when hasCar is true, or NEGATIVE.
		 *
		 *  Action:
		 *      POSITIVE -> pattern put Result as true
		 *      NEGATIVE -> pattern put Result as false
		 *               -> pattern put MEMO as Have no Car
		 *  Tail:
		 *      null
		 *
		 * Fact 2.
		 *  Condition:
		 *      POSITIVE when Budget > 3000, or NEGATIVE
		 *
		 *  Action:
		 *      POSITIVE -> pattern put Level as A
		 *      NEGATIVE -> pattern put Level as B
		 *
		 *  Tail:
		 *      null
		 */
		Rule rule = new Rule("Count");
		ActionFact p1Fact = new ActionFact("Phase1");
		ActionFact p21Fact = new ActionFact("Phase1-1");
		ActionFact p22Fact = new ActionFact("Phase1-2");
		p1Fact
			.setCondition(
				new ANDConditionGroup()
					.add(new Condition() {
						public Intention judge(Pattern pattern) {
							if (pattern.getNumber("Money").intValue() > 1000) {
								return Intention.POSITIVE;
							}
							return Intention.NEGATIVE;
						}
					})
					.add(new Condition() {
						public Intention judge(Pattern pattern) {
							return Intention.get(pattern.getNumber("Age").intValue() > 30);
						}
					})
			)
			.setTail(Intention.POSITIVE, p22Fact)
			.setTail(Intention.NEGATIVE, p21Fact);
		p21Fact
			.setCondition(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.get(pattern.getBoolean("hasBicycle"));
				}
			})
			.addAction(Intention.POSITIVE, new Actional() {
				public Actional doAction(Pattern pattern) {
					pattern.put("Result", true);
					return null;
				}
			})
			.addAction(Intention.NEGATIVE, new Actional() {
				public Actional doAction(Pattern pattern) {
					pattern.put("Result", false);
					pattern.put("MEMO", "Have no Bicycle");
					return null;
				}
			});
		p22Fact
			.setCondition(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.get(pattern.getBoolean("hasCar"));
				}
			})
			.addAction(Intention.POSITIVE, new Actional() {
				public Actional doAction(Pattern pattern) {
					pattern.put("Result", true);
					return null;
				}
			})
			.addAction(Intention.NEGATIVE, new Actional() {
				public Actional doAction(Pattern pattern) {
					pattern.put("Result", false);
					pattern.put("MEMO", "Have no Car");
					return null;
				}
			});
		rule.addFact(p1Fact);
		rule.addFact(
			new ActionFact()
				.setCondition(new Condition() {
					public Intention judge(Pattern pattern) {
						if (pattern.contain("Budget")) {
							return Intention.get(pattern.getNumber("Budget").intValue() > 3000);
						}
						return Intention.NEGATIVE;
					}
				})
				.addAction(Intention.POSITIVE, new Actional() {
					public Actional doAction(Pattern pattern) {
						pattern.put("Level", "A");
						return null;
					}
				})
				.addAction(Intention.NEGATIVE, new Actional() {
					public Actional doAction(Pattern pattern) {
						pattern.put("Level", "B");
						return null;
					}
				})
		);
		Pattern samplePattern1 =
			new Pattern()
				.put("Money", 1500)
				.put("Age", 35)
				.put("hasCar", false)
				.put("hasBicycle", false);
		rule.evaluate(samplePattern1);
		Assert.assertEquals(false, samplePattern1.getBoolean("Result"));
		Assert.assertEquals("Have no Car", samplePattern1.getString("MEMO"));
		Pattern samplePattern2 =
			new Pattern()
				.put("Money", 1500)
				.put("Age", 11)
				.put("hasCar", false)
				.put("hasBicycle", false);
		rule.evaluate(samplePattern2);
		Assert.assertEquals(false, samplePattern2.getBoolean("Result"));
		Assert.assertEquals("Have no Bicycle", samplePattern2.getString("MEMO"));
		Pattern samplePattern3 =
			new Pattern()
				.put("Budget", 5000)
				.put("Money", 1500)
				.put("Age", 35)
				.put("hasCar", true)
				.put("hasBicycle", false);
		rule.evaluate(samplePattern3);
		Assert.assertEquals(true, samplePattern3.getBoolean("Result"));
		Assert.assertEquals("A", samplePattern3.getString("Level"));
	}
}