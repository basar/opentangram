package com.basarc.opentangram.game;

import com.basarc.opentangram.util.Utils;

/**
 * This class can be thought as a Vector and it contains common operations.
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
	 * Calculates vector magnitude
	 * 
	 * @return length of vector
	 */
	public double magnitude() {
		return magnitude(new Position(0, 0), this);
	}

	/**
	 * Calculates vector magnitude
	 * 
	 * @param p1 started point
	 * @param p2 finished point (or vice versa)
	 * @return length of vector
	 */
	public static double magnitude(Position p1, Position p2) {
		double xMagnitude = Math.abs(p1.x - p2.x);
		double yMagnitude = Math.abs(p1.y - p2.y);
		return Utils.sqrt(Math.abs(xMagnitude * xMagnitude + yMagnitude
				* yMagnitude));
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

}
