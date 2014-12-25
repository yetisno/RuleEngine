package org.yetiz.service.rulengine.core.pattern;

import org.junit.Assert;
import org.junit.Test;
import org.yetiz.service.rulengine.core.exception.PatternUnitValueTypeErrorException;

import java.util.Calendar;

public class CalendarPatternUnitTest {

	@Test
	public void testGet() throws Exception {
		Calendar calendar = Calendar.getInstance();
		CalendarPatternUnit calendarPatternUnit = new CalendarPatternUnit("C", calendar);
		Assert.assertEquals(calendar, calendarPatternUnit.get());
	}

	@Test(expected = PatternUnitValueTypeErrorException.class)
	public void testSet() throws Exception {
		Calendar calendar = Calendar.getInstance();
		CalendarPatternUnit calendarPatternUnit = new CalendarPatternUnit("C", calendar);
		calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 1);
		calendarPatternUnit.set(calendar);
		Assert.assertEquals(calendar, calendarPatternUnit.get());
		calendarPatternUnit.set("");
	}

	@Test
	public void testWrap() throws Exception {
		Assert.assertNotNull(new CalendarPatternUnit().wrap("C", Calendar.getInstance()));
		Assert.assertNull(new CalendarPatternUnit().wrap("C", ""));
	}
}