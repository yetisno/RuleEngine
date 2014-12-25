package org.yetiz.service.rulengine.core.pattern;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.exception.ParseFormatErrorException;
import org.yetiz.service.rulengine.core.exception.PatternUnitNotExistException;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PatternTest {
	Pattern pattern;

	@Before
	public void setUp() throws Exception {
		pattern = new Pattern();
		Map<String, PatternUnit> patternUnitMap = new HashMap<String, PatternUnit>();
		patternUnitMap.put("S1", new StringPatternUnit("S1", "StringResult"));
		pattern = new Pattern(patternUnitMap);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testPut() throws Exception {
		pattern.put("I", 22);
		Assert.assertEquals(22, pattern.getNumber("I"));
	}

	@Test
	public void testPut1() throws Exception {
		pattern.put(new NumberPatternUnit("I", 22));
		Assert.assertEquals(22, pattern.getNumber("I"));
	}

	@Test
	public void testEntrySet() throws Exception {
		pattern.put(new NumberPatternUnit("I", 22));
		for (Map.Entry<String, PatternUnit> entry : pattern.entrySet()) {
			Assert.assertNotNull(entry.getKey());
		}
	}

	@Test
	public void testGetPatternUnit() throws Exception {
		Assert.assertEquals("StringResult", pattern.getPatternUnit("S1").<String>get());
	}

	@Test
	public void testGet() throws Exception {
		Assert.assertEquals("StringResult", pattern.<String>get("S1"));
	}

	@Test(expected = ParseFormatErrorException.class)
	public void testGet1() throws Exception {
		Assert.assertEquals("StringResult", pattern.get("S1", String.class));
		Assert.assertEquals("StringResult", pattern.get("S1", Integer.class));
	}

	@Test
	public void testGetString() throws Exception {
		Assert.assertEquals("StringResult", pattern.getString("S1"));
	}

	@Test
	public void testGetBigDecimal() throws Exception {
		pattern.put("T", new BigDecimal(11));
		Assert.assertEquals(11, pattern.getBigDecimal("T").intValue());
	}

	@Test
	public void testGetNumber() throws Exception {
		pattern.put("T", new Integer(11));
		Assert.assertEquals(11, pattern.getNumber("T").intValue());
	}

	@Test(expected = PatternUnitNotExistException.class)
	public void testGetCalendar() throws Exception {
		Calendar calendar = Calendar.getInstance();
		pattern.put("T", calendar);
		Assert.assertEquals(calendar, pattern.getCalendar("T"));
		pattern.get("SSS");
	}

	@Test
	public void testSize() throws Exception {
		Assert.assertEquals(new Integer(1), pattern.size());
	}

	@Test
	public void testGetBoolean() throws Exception {
		pattern.put("B", false);
		Assert.assertEquals(false, pattern.getBoolean("B"));
	}

	@Test
	public void testHasPatternUnit() throws Exception {
		pattern.put("B", false);
		Assert.assertTrue(pattern.contain("B"));
		Assert.assertFalse(pattern.contain("C"));
	}

	@Test
	public void testClear() throws Exception {
		pattern.put("B", false);
		pattern.clear();
		Assert.assertFalse(pattern.contain("B"));
	}
}