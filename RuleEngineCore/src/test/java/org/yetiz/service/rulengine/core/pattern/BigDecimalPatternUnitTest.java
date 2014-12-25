package org.yetiz.service.rulengine.core.pattern;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalPatternUnitTest {
	BigDecimalPatternUnit patternUnit;

	@Test
	public void testGet() throws Exception {
		patternUnit = new BigDecimalPatternUnit("B", new BigDecimal("10.1"));
		Assert.assertEquals(new BigDecimal("10.1"), patternUnit.get());
	}

	@Test
	public void testSet() throws Exception {
		patternUnit = new BigDecimalPatternUnit("B", new BigDecimal("10.1"));
		patternUnit.set(new BigDecimal("10.2"));
		Assert.assertEquals(new BigDecimal("10.2"), patternUnit.get());
	}

	@Test
	public void testWrap() throws Exception {
		Assert.assertEquals(new BigDecimal("10.3"), new BigDecimalPatternUnit().wrap("B", new BigDecimal("10.3")).get());
		Assert.assertNull(new BigDecimalPatternUnit().wrap("11", new Pattern()));
	}
}