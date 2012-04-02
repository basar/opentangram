package com.basarc.opentangram.game;

import java.util.ArrayList;

/**
 * 
 * @author basar
 * 
 */
public abstract class Shape {

	protected ArrayList<Position> vertices;

	private Position center;

	public Shape(Position center) {
		this.center = center;
	}

	public void rotate(double degreeInAngle) {

	}

}
