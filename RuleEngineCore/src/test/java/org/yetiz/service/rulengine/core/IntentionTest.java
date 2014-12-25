/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.yetiz.service.rulengine.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntentionTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGet() throws Exception {
		Assert.assertTrue(Intention.POSITIVE.get());
		Assert.assertFalse(Intention.NEGATIVE.get());
	}

	@Test
	public void testGet1() throws Exception {
		Assert.assertTrue(Intention.get(true).get());
		Assert.assertFalse(Intention.get(false).get());
	}
}