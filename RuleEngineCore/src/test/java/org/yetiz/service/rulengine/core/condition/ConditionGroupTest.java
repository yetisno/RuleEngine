package org.yetiz.service.rulengine.core.condition;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.pattern.Pattern;

public class ConditionGroupTest {
	ConditionGroup conditionGroup = new ConditionGroup() {
		public Intention judge(Pattern pattern) {
			return null;
		}
	};

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAdd() throws Exception {
		conditionGroup
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return null;
				}
			})
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return null;
				}
			});
		Assert.assertEquals(2, conditionGroup.getConditions().size());
	}

	@Test
	public void testRemove() throws Exception {
		conditionGroup
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return null;
				}
			})
			.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return null;
				}
			})
		.remove(1);
		Assert.assertEquals(1, conditionGroup.getConditions().size());
	}

	@Test
	public void testGetConditions() throws Exception {
		for (int i = 0; i < 10000; i++) {
			conditionGroup.add(new Condition() {
				public Intention judge(Pattern pattern) {
					return null;
				}
			});
		}
		Assert.assertEquals(10000, conditionGroup.getConditions().size());
	}
}