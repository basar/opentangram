package com.basarc.opentangram.game;

import java.util.ArrayList;

import com.basarc.opentangram.util.Utils;

/**
 * The class represents a positive convex polygon
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
		this.center = new Position(0f, 0f);
	}

	/**
	 * Creates new shape object at the given position
	 * 
	 * @param p
	 */
	public Shape(Position p) {
		this();
		changePosition(p);
	}

	protected abstract void initialize();

	/**
	 * Rotates shape around its center
	 * 
	 * @param angleInDegree degree of the rotation
	 */
	public void rotate(float angleInDegree) {
		for (Position p : vertices) {
			p.rotate(center, angleInDegree);
		}
	}

	/**
	 * Scales shape
	 * 
	 * @param scale
	 */
	public void scale(float scale) {
		for (Position p : vertices) {
			p.scale(center, scale);
		}
	}

	/**
	 * Translates shape to the given new position
	 * 
	 * @param newPos
	 */
	public void changePosition(Position newPos) {

		float x = newPos.x - center.x;
		float y = newPos.y - center.y;
		this.center = newPos;
		Position directionVector = new Position(x, y);
		for (Position p : vertices) {
			p.add(directionVector);
		}

	}

	/**
	 * moves shape towards the given direction vector
	 * 
	 * @param directionVector
	 */
	public void moveTo(Position directionVector) {
		this.center.add(directionVector);
		for (Position p : vertices) {
			p.add(directionVector);
		}
	}

	/**
	 * returns area of the shape using following the formula
	 * http://mathworld.wolfram.com/PolygonArea.html
	 * 
	 * @return
	 */
	public float calculateArea() {

		int size = vertices.size();
		float sum1 = 0;
		float sum2 = 0;

		for (int i = 0; i < size; i++) {
			sum1 = sum1 + (getVertex(i).x * getVertex((i + 1) % size).y);
			sum2 = sum2 + (getVertex(i).y * getVertex((i + 1) % size).x);
		}
		sum1 = Utils.round(sum1);
		sum2 = Utils.round(sum2);
		return (0.5f * (sum1 - sum2));
	}

	/**
	 * This method specifies either any given point lies interior of the shape
	 * or not. this algorithm is implemented thanks to the following web site.
	 * (It is also known as ray casting algorithm)
	 * http://paulbourke.net/geometry/insidepoly/
	 * 
	 * @param p position
	 * @return
	 */
	public boolean isInside(Position p) {

		int count = 0;
		float xinterceptors = 0;
		int size = vertices.size();
		Position p1, p2;

		p1 = getVertex(0);
		for (int i = 1; i < size + 1; i++) {
			p2 = getVertex(i % size);
			if (p.y > Math.min(p1.y, p2.y)) {
				if (p.y <= Math.max(p1.y, p2.y)) {
					if (p.x <= Math.max(p1.x, p2.x)) {
						if (p1.y != p2.y) {
							xinterceptors = (p.y - p1.y) * (p2.x - p1.x)
									/ (p2.y - p1.y) + p1.x;
							if (p1.x == p2.x || p.x <= xinterceptors) {
								count++;
							}
						}
					}
				}
			}
			p1 = p2;
		}

		return count % 2 != 0;
	}

	public boolean isInside(float x, float y) {
		Position p = new Position(x, y);
		return isInside(p);
	}

	protected void createVertex(float x, float y) {
		vertices.add(new Position(x, y));
	}

	protected void createVertex(Position p) {
		vertices.add(new Position(p));
	}

	protected void createVertex(int index, float x, float y) {
		vertices.add(index, new Position(x, y));
	}

	public ArrayList<Position> getVertices() {
		return vertices;
	}

	public Position getVertex(int index) {
		if (index > vertices.size()) {
			throw new IllegalArgumentException(index
					+ " is bigger than list size");
		}
		return vertices.get(index);
	}

	public Position getCenter() {
		return center;
	}

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
	 * Helper method that is used to compare two vertices list
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
