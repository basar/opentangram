package com.basarc.opentangram.game;

import java.util.ArrayList;

/**
 * 
 * @author basar
 * 
 */
public abstract class Shape {

	/**
	 * vertices of the polygon
	 */
	protected ArrayList<Position> vertices;

	/**
	 * center point
	 */
	protected Position center;

	/**
	 * default constructor
	 */
	public Shape() {
		vertices = new ArrayList<Position>();
		this.center = new Position(0, 0);
	}

	/**
	 * Rotates shape around its center
	 * 
	 * @param angleInDegree degree of the rotation
	 */
	public void rotate(double angleInDegree) {
		for (Position p : vertices) {
			p.rotate(center, angleInDegree);
		}
	}

	/**
	 * Scales shape
	 * 
	 * @param scale
	 */
	public void scale(double scale) {
		for (Position p : vertices) {
			p.scale(scale);
		}
	}

	/**
	 * Translates shape to the given new position
	 * 
	 * @param newPos
	 */
	public void changePosition(Position newPos) {
		double x = newPos.getX() - center.getX();
		double y = newPos.getY() - center.getY();
		this.center = newPos;
		Position directionVector = new Position(x, y);
		for (Position p : vertices) {
			p.add(directionVector);
		}
	}

	public void moveTo(Position directionVector) {
		this.center.add(directionVector);
		for (Position p : vertices) {
			p.add(directionVector);
		}

	}

	public ArrayList<Position> getVertices() {
		return vertices;
	}

	public Position getVertice(int index) {
		if (index > vertices.size()) {
			throw new IllegalArgumentException(index
					+ " is bigger than list size");
		}
		return vertices.get(index);
	}

	public Position getCenter() {
		return center;
	}

	protected abstract void initialize();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		result = prime * result
				+ ((vertices == null) ? 0 : vertices.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shape other = (Shape) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (vertices == null) {
			if (other.vertices != null)
				return false;
		} else if (!this.compareVertices(other))
			return false;
		return true;
	}

	/**
	 * Helper method that is used to compare two vertice list
	 * 
	 * @param other
	 * @return
	 */
	private boolean compareVertices(Shape other) {

		boolean result = true;

		for (Position p1 : vertices) {
			boolean kontrol = false;
			for (Position p2 : other.vertices) {
				if (p1.equals(p2)) {
					kontrol = true;
				}
			}
			if (!kontrol) {
				result = false;
				break;
			}
		}

		return result;
	}
}
