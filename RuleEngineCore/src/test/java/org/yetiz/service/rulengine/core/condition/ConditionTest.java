package org.yetiz.service.rulengine.core.condition;

import org.junit.Before;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.pattern.Pattern;

import static org.junit.Assert.*;

public class ConditionTest {

	@Test
	public void setUp() throws Exception {
		Condition condition;
		condition = new Condition("") {
			public Intention judge(Pattern pattern) {
				return null;
			}
		};
		condition = new Condition() {
			public Intention judge(Pattern pattern) {
				return null;
			}
		};
	}
}