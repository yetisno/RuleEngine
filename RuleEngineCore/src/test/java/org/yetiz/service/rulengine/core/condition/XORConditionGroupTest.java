package org.yetiz.service.rulengine.core.condition;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.pattern.Pattern;

public class XORConditionGroupTest {
	XORConditionGroup xorConditionGroup;

	@Before
	public void setUp() throws Exception {
		xorConditionGroup = new XORConditionGroup();
	}

	@Test
	public void testJudge() throws Exception {
		xorConditionGroup
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			})
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			});
		Assert.assertEquals(Intention.NEGATIVE, xorConditionGroup.judge(null));
		xorConditionGroup
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			});
		Assert.assertEquals(Intention.POSITIVE, xorConditionGroup.judge(null));
	}

	@Test
	public void testAdd() throws Exception {
		xorConditionGroup
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			})
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			});
		Assert.assertEquals(2, xorConditionGroup.getConditions().size());
	}

	@Test
	public void testRemove() throws Exception {
		xorConditionGroup
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			})
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.POSITIVE;
				}
			}).remove(1);
		Assert.assertEquals(1, xorConditionGroup.getConditions().size());
	}
}