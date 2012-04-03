package com.basarc.opentangram.game;

import com.basarc.opentangram.util.Utils;

/**
 * This class can be considered as a vector and contains common operations.
 * 
 * @author basar
 * 
 */
public class Position {

	/**
	 * x coordinate
	 */
	private double x;
	/**
	 * y coordinate
	 */
	private double y;

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
	public Position(double x, double y) {
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
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Rotates the position object (relative to (0,0))
	 * 
	 * @param angleInDegree degree of the rotation
	 */
	public void rotate(double angleInDegree) {
		rotate(new Position(0, 0), angleInDegree);
	}

	/**
	 * Rotates this position around the given center point
	 * 
	 * @param center center point
	 * @param angleInDegree degree of the rotation
	 */
	public void rotate(Position center, double angleInDegree) {

		double cosTheta = Utils.cos(angleInDegree);
		double sinTheta = Utils.sin(angleInDegree);

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
	public void scale(double scale) {
		this.x = this.x * scale;
		this.y = this.y * scale;
	}

	/**
	 * Adds another vector to this vector
	 * 
	 * @param p
	 */
	public void add(Position p) {
		this.x = this.x + p.getX();
		this.y = this.y + p.getY();
	}

	/**
	 * Calculates vector magnitude
	 * 
	 * @return magnitude of the vector
	 */
	public double magnitude() {
		return distance(new Position(0, 0), this);
	}

	/**
	 * Calculates distance
	 * 
	 * @param p1 started point
	 * @param p2 finished point (or vice versa)
	 * @return magnitude of the vector
	 */
	public static double distance(Position p1, Position p2) {
		double xMagnitude = Math.abs(p1.x - p2.x);
		double yMagnitude = Math.abs(p1.y - p2.y);
		return Utils.sqrt(xMagnitude * xMagnitude + yMagnitude * yMagnitude);
	}

	/**
	 * Value of x coordinate
	 * 
	 * @return
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Value of y coordinate
	 * 
	 * @return
	 */
	public double getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

}
