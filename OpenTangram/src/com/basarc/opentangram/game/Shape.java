package com.basarc.opentangram.game;

import java.util.ArrayList;

/**
 * 
 * @author basar
 * 
 */
public abstract class Shape {

	protected ArrayList<Position> vertices;

	protected Position center;

	public Shape() {
		vertices = new ArrayList<Position>();
		this.center = new Position(0, 0);
	}

	public void rotate(double angleInDegree) {
		for (Position p : vertices) {
			p.rotate(center, angleInDegree);
		}
	}

	public void scale(double scale) {
		for (Position p : vertices) {
			p.scale(scale);
		}
	}

	public void moveTo(Position newPos) {
		double x = newPos.getX() - center.getX();
		double y = newPos.getY() - center.getY();
		this.center = newPos;
		Position directionVector = new Position(x, y);
		for (Position p : vertices) {
			p.add(directionVector);
		}
	}

	public ArrayList<Position> getVertices() {
		return vertices;
	}

	public Position getCenter() {
		return center;
	}

	public abstract void initialize();

}
