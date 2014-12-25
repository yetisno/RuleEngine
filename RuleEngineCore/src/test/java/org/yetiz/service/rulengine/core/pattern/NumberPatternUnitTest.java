package org.yetiz.service.rulengine.core.pattern;

import org.junit.Assert;
import org.junit.Test;

public class NumberPatternUnitTest {

	@Test
	public void testGet() throws Exception {
		NumberPatternUnit numberPatternUnit = new NumberPatternUnit("N", 22l);
		Assert.assertEquals(22l, numberPatternUnit.get());
	}

	@Test
	public void testSet() throws Exception {
		NumberPatternUnit numberPatternUnit = new NumberPatternUnit("N", 22l);
		numberPatternUnit.set(33);
		Assert.assertEquals(33, numberPatternUnit.get());
	}

	@Test
	public void testWrap() throws Exception {
		Assert.assertNotNull(new NumberPatternUnit().wrap("N", 22l));
		Assert.assertNull(new NumberPatternUnit().wrap("N", new Pattern()));
	}
}