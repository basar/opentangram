package com.basarc.opentangram.test;

import junit.framework.TestCase;

import com.basarc.opentangram.util.Utils;

public class UtilTest extends TestCase {

	public void testRound() {

	}

	public void testAngle() {

		float rad = (float) Math.toRadians(30);
		float test = (float) Math.sin(rad);

		assertEquals(test == 0.5, true);

		double value1 = Utils.sin(30);
		assertEquals(value1 == 0.5, true);

		double value2 = Utils.sin(90);
		assertEquals(value2 == 1, true);

		assertEquals(Utils.sin(45) == Utils.cos(45), true);
	}

}
