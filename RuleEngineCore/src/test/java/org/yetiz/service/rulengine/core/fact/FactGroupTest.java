package org.yetiz.service.rulengine.core.fact;

import org.junit.Assert;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;

public class FactGroupTest {
	FactGroup factGroup;

	@Test
	public void testEvaluate() throws Exception {
		factGroup = new FactGroup() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		};
		Assert.assertEquals(Intention.POSITIVE, factGroup.evaluate(null).getIntention());
	}

	@Test
	public void testAdd() throws Exception {
		factGroup = new FactGroup() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		};
		factGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return null;
			}
		});
		Assert.assertEquals(1, factGroup.facts.size());
	}

	@Test
	public void testRemove() throws Exception {
		factGroup = new FactGroup() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		};
		factGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return null;
			}
		}).add(new Fact() {
			@Override
			public Intention getIntention() {
				return null;
			}
		}).remove(0);
		Assert.assertEquals(1, factGroup.facts.size());
	}

	@Test
	public void testGetFacts() throws Exception {
		factGroup = new FactGroup() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		};
		factGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return null;
			}
		}).add(new Fact() {
			@Override
			public Intention getIntention() {
				return null;
			}
		});
		Assert.assertEquals(2, factGroup.getFacts().size());
	}
}