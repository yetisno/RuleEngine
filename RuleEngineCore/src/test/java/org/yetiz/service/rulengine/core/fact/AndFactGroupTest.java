package org.yetiz.service.rulengine.core.fact;

import org.junit.Assert;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.exception.FactNotEvaluatedException;

public class AndFactGroupTest {

	@Test (expected = FactNotEvaluatedException.class)
	public void testGetIntention() throws Exception {
		AndFactGroup andFactGroup = new AndFactGroup();
		andFactGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		}).add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		}).evaluate(null);
		Assert.assertEquals(Intention.POSITIVE, andFactGroup.getIntention());
		andFactGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.NEGATIVE;
			}
		}).evaluate(null);
		Assert.assertEquals(Intention.NEGATIVE, andFactGroup.getIntention());
		andFactGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.NEGATIVE;
			}
		});
		Assert.assertEquals(Intention.NEGATIVE, andFactGroup.getIntention());
	}
}