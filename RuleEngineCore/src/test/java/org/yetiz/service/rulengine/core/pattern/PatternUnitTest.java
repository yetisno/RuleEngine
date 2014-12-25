/*
 * Copyright (c) 2014.
*/

package org.yetiz.service.rulengine.core.pattern;

import org.junit.Assert;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Action;
import org.yetiz.service.rulengine.core.exception.ParseFormatErrorException;

public class PatternUnitTest {

	@Test(expected = ParseFormatErrorException.class)
	public void test_get() throws Exception {
		PatternUnit patternUnit = new PatternUnit("T", "TR") {
			@Override
			public Object get() {
				return _get(Object.class);
			}

			@Override
			public <R extends PatternUnit> R wrap(String id, Object value) {
				return null;
			}
		};
		Assert.assertEquals("TR", patternUnit._get(String.class));
		Assert.assertEquals("TR", patternUnit._get(Action.class));
	}

	@Test
	public void testSet() throws Exception {
		PatternUnit patternUnit = new PatternUnit("T", "TR") {
			@Override
			public Object get() {
				return _get(Object.class);
			}

			@Override
			public <R extends PatternUnit> R wrap(String id, Object value) {
				return null;
			}
		};
		patternUnit.set("TS");
		Assert.assertEquals("TS", patternUnit.get());
	}

	@Test
	public void testGetValueClass() throws Exception {
		PatternUnit patternUnit = new PatternUnit("T", "TR") {
			@Override
			public Object get() {
				return _get(Object.class);
			}

			@Override
			public <R extends PatternUnit> R wrap(String id, Object value) {
				return null;
			}
		};
		Assert.assertEquals(String.class, patternUnit.getValueClass());
	}
}