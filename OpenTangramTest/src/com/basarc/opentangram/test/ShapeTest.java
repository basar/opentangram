package com.basarc.opentangram.test;

import junit.framework.TestCase;

import com.basarc.opentangram.game.Position;
import com.basarc.opentangram.game.Square;

public class ShapeTest extends TestCase {

	public void testRotate() {
		Square sq = new Square();
		sq.rotate(90);

		assertEquals(sq.getVertices().get(0).getX() == -1, true);
		assertEquals(sq.getVertices().get(0).getY() == 1, true);

	}

	public void testMove() {
		Square sq = new Square();
		sq.moveTo(new Position(5, 5));

		assertEquals(sq.getVertices().get(0).getX() == 6, true);
		assertEquals(sq.getVertices().get(0).getY() == 6, true);
	}

	public void testScale() {

	}

}
