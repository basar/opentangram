package com.basarc.opentangram.test;

import junit.framework.TestCase;

import com.basarc.opentangram.game.BigTriangle;
import com.basarc.opentangram.game.MediumTriangle;
import com.basarc.opentangram.game.Parallelogram;
import com.basarc.opentangram.game.Position;
import com.basarc.opentangram.game.SmallTriangle;
import com.basarc.opentangram.game.Square;

public class ShapeTest extends TestCase {

	public void testRotate() {

		Square sq = new Square();
		sq.rotate(90);

		assertEquals(sq.getVertex(0).equals(new Position(-1, 1)), true);
		assertEquals(sq.getVertex(1).equals(new Position(-1, -1)), true);
		assertEquals(sq.getVertex(2).equals(new Position(1, -1)), true);
		assertEquals(sq.getVertex(3).equals(new Position(1, 1)), true);

		SmallTriangle st = new SmallTriangle();
		st.rotate(270);

		assertEquals(st.getVertex(0)
				.equals(new Position(SmallTriangle.P0.y, 0)), true);

	}

	public void testChangePosition() {

		Square sq = new Square();
		sq.changePosition(new Position(5, 5));

		assertEquals(sq.getVertex(0).equals(new Position(6, 6)), true);
		assertEquals(sq.getVertex(1).equals(new Position(4, 6)), true);
		assertEquals(sq.getVertex(2).equals(new Position(4, 4)), true);
		assertEquals(sq.getVertex(3).equals(new Position(6, 4)), true);

	}

	public void testScale() {
		Square s1 = new Square(20, 20);
		Square s2 = new Square(20, 20);
		s2.scale(2);
		assertEquals(s1.getCenter().equals(s2.getCenter()), true);

	}

	public void testInside() {

		Square s1 = new Square(2, 2);
		assertEquals(s1.isInside(new Position(2, 2)), true);
		assertEquals(s1.isInside(new Position(4, 4)), false);
		assertEquals(s1.isInside(new Position(3, 3)), true);
		assertEquals(s1.isInside(new Position(1.5f, 1.5f)), true);

		SmallTriangle st = new SmallTriangle();
		assertEquals(st.isInside(-1, -1), false);
		assertEquals(st.isInside(-0.5f, -0.5f), false);

	}

	public void testMoveTo() {

		Square sq = new Square(-2, 2);
		sq.moveTo(new Position(1, 1));

		assertEquals(sq.getVertex(0).equals(new Position(0, 4)), true);
		assertEquals(sq.getVertex(1).equals(new Position(-2, 4)), true);
		assertEquals(sq.getVertex(2).equals(new Position(-2, 2)), true);
		assertEquals(sq.getVertex(3).equals(new Position(0, 2)), true);

	}

	public void testEquals() {

		Square sq1 = new Square(3, 3);
		Square sq2 = new Square();
		assertEquals(sq1.equals(sq2), false);

		Square sq3 = new Square();
		sq3.rotate(180);
		assertEquals(sq3.equals(sq2), true);

		sq3.rotate(45);
		assertEquals(sq3.equals(sq2), false);

	}

	public void testArea() {

		Square sq = new Square();
		assertEquals(sq.calculateArea() == 4, true);

		SmallTriangle st = new SmallTriangle();
		assertEquals(st.calculateArea() == 2, true);

		BigTriangle bt = new BigTriangle();
		assertEquals(bt.calculateArea() == 8, true);

		Parallelogram po = new Parallelogram();
		assertEquals(po.calculateArea() == 4, true);

		MediumTriangle mt = new MediumTriangle();
		assertEquals(mt.calculateArea() == 4, true);

	}
}
