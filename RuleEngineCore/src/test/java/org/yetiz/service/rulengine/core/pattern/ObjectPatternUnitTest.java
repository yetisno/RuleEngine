package org.yetiz.service.rulengine.core.pattern;

import org.junit.Assert;
import org.junit.Test;

public class ObjectPatternUnitTest {

	@Test
	public void testGet() throws Exception {
		Pattern pattern = new Pattern();
		ObjectPatternUnit objectPatternUnit = new ObjectPatternUnit("11", pattern);
		Assert.assertEquals(pattern, objectPatternUnit.get());
	}

	@Test
	public void testWrap() throws Exception {
		Assert.assertNotNull(new ObjectPatternUnit().wrap("O", new Pattern()));
		Assert.assertNull(new ObjectPatternUnit().wrap("O", null));
	}
}