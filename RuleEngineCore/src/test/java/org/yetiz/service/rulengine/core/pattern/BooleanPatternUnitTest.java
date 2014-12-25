package org.yetiz.service.rulengine.core.pattern;

import org.junit.Assert;
import org.junit.Test;

public class BooleanPatternUnitTest {
	BooleanPatternUnit patternUnit;

	@Test
	public void testGet() throws Exception {
		patternUnit = new BooleanPatternUnit("B", Boolean.FALSE);
		Assert.assertFalse(patternUnit.get());
		patternUnit = new BooleanPatternUnit("B", false);
		Assert.assertFalse(patternUnit.get());
	}

	@Test
	public void testSet() throws Exception {
		patternUnit = new BooleanPatternUnit("B", Boolean.FALSE);
		Assert.assertFalse(patternUnit.get());
		patternUnit.set(true);
		Assert.assertTrue(patternUnit.get());
	}

	@Test
	public void testWrap() throws Exception {
		patternUnit = new BooleanPatternUnit().wrap("B", false);
		Assert.assertFalse(patternUnit.get());
		Assert.assertNull(new BooleanPatternUnit().wrap("11", new Pattern()));
	}
}