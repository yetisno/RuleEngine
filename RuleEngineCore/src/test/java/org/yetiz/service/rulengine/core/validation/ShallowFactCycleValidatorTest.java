package org.yetiz.service.rulengine.core.validation;

import org.junit.Assert;
import org.junit.Test;
import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.fact.ActionFact;

public class ShallowFactCycleValidatorTest {

	@Test
	public void testValidate() throws Exception {
		ActionFact actionFact = new ActionFact();
		ShallowFactCycleValidator shallowFactCycleValidator = new ShallowFactCycleValidator();
		actionFact.setTail(Intention.NEGATIVE, new ActionFact().setTail(Intention.POSITIVE, new ActionFact()));
		Assert.assertTrue(shallowFactCycleValidator.validate(actionFact));
		ActionFact actionFact1 = new ActionFact();
		actionFact1
			.setTail(Intention.POSITIVE, new ActionFact()
				.setTail(Intention.POSITIVE, new ActionFact())
				.setTail(Intention.NEGATIVE, new ActionFact()))
			.setTail(Intention.NEGATIVE, new ActionFact()
				.setTail(Intention.POSITIVE, new ActionFact())
				.setTail(Intention.NEGATIVE, new ActionFact()
					.setTail(Intention.NEGATIVE, new ActionFact())
					.setTail(Intention.POSITIVE, actionFact1)));
		actionFact.setTail(Intention.POSITIVE, actionFact1);
		Assert.assertFalse(shallowFactCycleValidator.validate(actionFact));
		ActionFact head = new ActionFact();
		ActionFact actionFact2 = head;
		for (int i = 0; i < 1005; i++) {
			ActionFact actionFact3 = new ActionFact();
			actionFact2.setTail(Intention.POSITIVE, actionFact3);
			actionFact2 = actionFact3;
		}
		Assert.assertFalse(shallowFactCycleValidator.validate(head));
	}
}