package org.yetiz.service.rulengine.core.fact;

import org.junit.Assert;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.exception.FactNotEvaluatedException;

import static org.junit.Assert.*;

public class XORFactGroupTest {

	@Test(expected = FactNotEvaluatedException.class)
	public void testGetIntention() throws Exception {
		XORFactGroup xorFactGroup = new XORFactGroup();
		xorFactGroup.add(new Fact() {
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
		Assert.assertEquals(Intention.NEGATIVE, xorFactGroup.getIntention());
		xorFactGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		}).evaluate(null);
		Assert.assertEquals(Intention.POSITIVE, xorFactGroup.getIntention());
		xorFactGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		}).evaluate(null);
		Assert.assertEquals(Intention.NEGATIVE, xorFactGroup.getIntention());
		xorFactGroup.add(new Fact() {
			@Override
			public Intention getIntention() {
				return Intention.POSITIVE;
			}
		});
		Assert.assertEquals(Intention.NEGATIVE, xorFactGroup.getIntention());
	}}