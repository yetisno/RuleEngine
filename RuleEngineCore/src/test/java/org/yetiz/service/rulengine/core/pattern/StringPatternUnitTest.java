package org.yetiz.service.rulengine.core.pattern;

import org.junit.Assert;
import org.junit.Test;

public class StringPatternUnitTest {

	@Test
	public void testGet() throws Exception {
		StringPatternUnit stringPatternUnit = new StringPatternUnit("S", "String");
		Assert.assertEquals("String", stringPatternUnit.get());
	}

	@Test
	public void testSet() throws Exception {
		StringPatternUnit stringPatternUnit = new StringPatternUnit("S", "String");
		stringPatternUnit.set("SSS");
		Assert.assertEquals("SSS", stringPatternUnit.get());
	}

	@Test
	public void testWrap() throws Exception {
		Assert.assertNotNull(new StringPatternUnit().wrap("S", "SS"));
		Assert.assertNull(new StringPatternUnit().wrap("S", new Pattern()));
	}
}