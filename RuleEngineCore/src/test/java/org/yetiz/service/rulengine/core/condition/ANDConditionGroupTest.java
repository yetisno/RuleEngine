package org.yetiz.service.rulengine.core.condition;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.pattern.Pattern;

public class ANDConditionGroupTest {
	ANDConditionGroup andConditionGroup;

	@Before
	public void setUp() throws Exception {
		andConditionGroup = new ANDConditionGroup();
	}

	@Test
	public void testJudge() throws Exception {
		andConditionGroup
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
		Assert.assertEquals(Intention.POSITIVE, andConditionGroup.judge(null));
		andConditionGroup
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return Intention.NEGATIVE;
				}
			});
		Assert.assertEquals(Intention.NEGATIVE, andConditionGroup.judge(null));
	}

	@Test
	public void testAdd() throws Exception {
		andConditionGroup
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
		Assert.assertEquals(2, andConditionGroup.getConditions().size());
	}

	@Test
	public void testRemove() throws Exception {
		andConditionGroup
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
		Assert.assertEquals(1, andConditionGroup.getConditions().size());
	}
}