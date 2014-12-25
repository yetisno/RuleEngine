package org.yetiz.service.rulengine.core.helper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.pattern.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class PatternUnitHelperTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testLoadRegisteredPatternUnitList() throws Exception {
		List<PatternUnit> list = new ArrayList<PatternUnit>();
		list.add(new BigDecimalPatternUnit());
		PatternUnitHelper.loadRegisteredPatternUnitList(list);
		Assert.assertEquals(BigDecimalPatternUnit.class, PatternUnitHelper.get("S", new BigDecimal("1")).getClass());
		Assert.assertNotNull(PatternUnitHelper.get("S", new Pattern()));
		list.add(new ObjectPatternUnit());
		PatternUnitHelper.loadRegisteredPatternUnitList(list);
		Assert.assertNotNull(PatternUnitHelper.get("S", new Pattern()));
	}

	@Test
	public void testGet() throws Exception {
		Assert.assertEquals(BigDecimalPatternUnit.class, PatternUnitHelper.get("S", new BigDecimal(1)).getClass());
		Assert.assertEquals(CalendarPatternUnit.class, PatternUnitHelper.get("S", Calendar.getInstance()).getClass());
		Assert.assertEquals(BooleanPatternUnit.class, PatternUnitHelper.get("S", Boolean.FALSE).getClass());
		Assert.assertEquals(NumberPatternUnit.class, PatternUnitHelper.get("S", new Long(1)).getClass());
		Assert.assertEquals(StringPatternUnit.class, PatternUnitHelper.get("S", "SS").getClass());
		Assert.assertEquals(ObjectPatternUnit.class, PatternUnitHelper.get("S", new Pattern()).getClass());
	}
}