package org.yetiz.service.rulengine.core.fact;

import org.junit.Assert;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.exception.FactNotEvaluatedException;

public class ORFactGroupTest {

	@Test(expected = FactNotEvaluatedException.class)
	public void testGetIntention() throws Exception {
		ORFactGroup orFactGroup = new ORFactGroup();
		orFactGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.NEGATIVE;
			}
		}).add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.NEGATIVE;
			}
		}).evaluate(null);
		Assert.assertEquals(Intention.NEGATIVE, orFactGroup.getIntention());
		orFactGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		}).evaluate(null);
		Assert.assertEquals(Intention.POSITIVE, orFactGroup.getIntention());
		orFactGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		});
		Assert.assertEquals(Intention.POSITIVE, orFactGroup.getIntention());
	}
}