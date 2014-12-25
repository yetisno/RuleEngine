package org.yetiz.service.rulengine.core.condition;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.pattern.Pattern;

public class ORConditionGroupTest {
	ORConditionGroup orConditionGroup;

	@Before
	public void setUp() throws Exception {
		orConditionGroup = new ORConditionGroup();
	}

	@Test
	public void testJudge() throws Exception {
		orConditionGroup
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
		Assert.assertEquals(Intention.POSITIVE, orConditionGroup.judge(null));
		orConditionGroup
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.NEGATIVE;
				}
			});
		Assert.assertEquals(Intention.POSITIVE, orConditionGroup.judge(null));
	}

	@Test
	public void testAdd() throws Exception {
		orConditionGroup
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
		Assert.assertEquals(2, orConditionGroup.getConditions().size());
	}

	@Test
	public void testRemove() throws Exception {
		orConditionGroup
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
		Assert.assertEquals(1, orConditionGroup.getConditions().size());
	}
}