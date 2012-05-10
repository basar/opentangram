package com.basarc.opentangram.game;

import com.basarc.opentangram.util.Utils;

/**
 * This class can be considered as a position vector and contains common vector
 * operations.
 * 
 * @author basar
 * 
 */
public class Position {

	/**
	 * x coordinate
	 */
	public float x;
	/**
	 * y coordinate
	 */
	public float y;

	/**
	 * Creates new position object. x=0 and y=0
	 */
	public Position() {
		setPosition(0, 0);
	}

	/**
	 * Creates new position object
	 * 
	 * @param x value of x coordinate
	 * @param y value of y coordinate
	 */
	public Position(float x, float y) {
		setPosition(x, y);
	}

	public Position(Position param) {
		this.x = param.x;
		this.y = param.y;
	}

	/**
	 * Set a new position
	 * 
	 * @param x value of x coordinate
	 * @param y value of y coordinate
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Rotates the position object (relative to (0,0))
	 * 
	 * @param angleInDegree degree of the rotation
	 */
	public void rotate(float angleInDegree) {
		rotate(new Position(0, 0), angleInDegree);
	}

	/**
	 * Rotates this position around the given center point
	 * 
	 * @param center center point
	 * @param angleInDegree degree of the rotation
	 */
	public void rotate(Position center, float angleInDegree) {

		float cosTheta = Utils.cos(angleInDegree);
		float sinTheta = Utils.sin(angleInDegree);

		Position temp = new Position(this);
		temp.x = temp.x - center.x;
		temp.y = temp.y - center.y;

		this.x = temp.x * cosTheta - temp.y * sinTheta;
		this.y = temp.x * sinTheta + temp.y * cosTheta;

		this.x = this.x + center.x;
		this.y = this.y + center.y;
	}

	/**
	 * Scales position
	 * 
	 * @param scale Scale value
	 */
	public void scale(float scale) {
		scale(new Position(0, 0), scale);
	}

	/**
	 * Scales position
	 * 
	 * @param center
	 * @param scale
	 */
	public void scale(Position center, float scale) {

		Position temp = new Position(this);
		temp.x = temp.x - center.x;
		temp.y = temp.y - center.y;

		temp.x = temp.x * scale;
		temp.y = temp.y * scale;

		this.x = temp.x + center.x;
		this.y = temp.y + center.y;

	}

	/**
	 * Adds param vector to this vector
	 * 
	 * @param p
	 */
	public void add(Position p) {
		this.x = this.x + p.x;
		this.y = this.y + p.y;
	}

	/**
	 * Multiplies with scalar
	 * 
	 * @param scalar
	 */
	public void multiply(float scalar) {
		this.x = this.x * scalar;
		this.y = this.y * scalar;
	}

	/**
	 * Calculates vector magnitude
	 * 
	 * @return magnitude of the vector
	 */
	public float magnitude() {
		return this.distance(new Position(0, 0));
	}

	/**
	 * Normalize the vector
	 */
	public void normalize() {
		float length = magnitude();
		this.x = this.x / length;
		this.y = this.y / length;
	}

	/**
	 * Calculates distance
	 * 
	 * @param p
	 * @return
	 */
	private float distance(Position p) {
		float xMagnitude = Math.abs(this.x - p.x);
		float yMagnitude = Math.abs(this.y - p.y);
		return Utils.sqrt(xMagnitude * xMagnitude + yMagnitude * yMagnitude);
	}

	/**
	 * Calculates angle between two vectors
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public float calculateAngle(Position p) {

		if (p == null)
			throw new IllegalArgumentException(
					"Param vector should not be null");

		Position zeroVect = new Position();

		if (this.equals(zeroVect) || p.equals(zeroVect))
			throw new IllegalArgumentException(
					"Vectors must be different than zero vector");

		float angle = (float) Math.atan2(perpDot(p), dot(p));

		float degree = (float) Math.toDegrees(angle);

		if (degree < 0) {
			degree = 360 + degree;
		}

		return degree;

	}

	private float dot(Position p) {
		return this.x * p.x + this.y * p.y;
	}

	private float perpDot(Position p) {
		return this.x * p.y - this.y * p.x;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
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
		Position other = (Position) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}

}
