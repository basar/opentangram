package com.basarc.opentangram.test;

import junit.framework.TestCase;

import com.basarc.opentangram.game.Position;
import com.basarc.opentangram.util.Utils;

public class PositionTest extends TestCase {

	public void testRotation() {

		Position p1 = new Position(1, 1);
		p1.rotate(45);
		assertEquals(p1.x == 0, true);
		double sqrt = Utils.sqrt(2);
		assertEquals(p1.y == sqrt, true);

		Position p2 = new Position(3, 3);
		p2.rotate(new Position(2, 2), 90);
		assertEquals(p2.x == 1, true);
		assertEquals(p2.y == 3, true);

		Position p3 = new Position(1.2f, 1.2f);
		p3.rotate(90);
		assertEquals(p3.x == -1.2f, true);
		assertEquals(p3.y == 1.2f, true);

		Position p4 = new Position(3, -3);
		p4.rotate(135);
		assertEquals(p4.x == 0, true);
		assertEquals(p4.y == p4.magnitude(), true);
	}

	public void testScale() {

		Position p1 = new Position(3, 3);
		p1.scale(5);
		assertEquals(p1.x == 15, true);
		assertEquals(p1.y == 15, true);

	}

	public void testEquals() {

		Position p1 = new Position(4, 4);
		Position p2 = new Position(4, 4);
		assertEquals(p1.equals(p2), true);

		Position p3 = new Position(1, 0);
		assertEquals(p3.equals(p1), false);

	}

}
