package com.basarc.opentangram.game;

import java.util.ArrayList;

/**
 * 
 * @author basar
 * 
 */
public class PuzzleBoard {

	private static PuzzleBoard instance = null;

	private int scale;

	private float width;

	private float height;

	private ArrayList<Shape> shapes;

	private Shape selected;

	private PuzzleBoard() {

	}

	public static PuzzleBoard getInstance() {
		if (instance == null) {
			instance = new PuzzleBoard();
		}
		return instance;
	}

	public void initialize(float width, float height) {

		this.height = height;
		this.width = width;

		shapes = new ArrayList<Shape>();
		selected = null;
		createShapes();

	}

	private void createShapes() {

		// scale value that used for all shapes
		scale = Math.round(Math.max(height, width) / 25);

		BigTriangle bt1 = new BigTriangle(scale, new Position(scale
				+ (scale * 2), scale - (scale / 2)));
		bt1.rotate(180);
		bt1.setTag(0);

		BigTriangle bt2 = new BigTriangle(scale, new Position(scale / 4,
				4 * scale));
		bt2.rotate(90);
		bt2.setTag(1);

		Square sqr = new Square(scale, new Position(7 * scale, scale + scale
				/ 2));
		sqr.setTag(2);

		Parallelogram po = new Parallelogram(scale, new Position(7 * scale, 2
				* scale + scale));
		po.setTag(3);

		shapes.add(po);
		shapes.add(sqr);
		shapes.add(bt1);
		shapes.add(bt2);
	}

	public void setSelectedShape(float x, float y) {
		Shape shape = getShape(x, y);
		setSelectedShape(shape);
	}

	public Shape getShape(float x, float y) {

		Shape shape = null;

		for (int i = shapes.size() - 1; i >= 0; i--) {
			Shape s = getShape(i);
			if (s.isInside(new Position(x, y))) {
				shape = s;
				break;
			}
		}

		return shape;
	}

	public void dragSelectedShapeTo(float x, float y) {
		if (selected != null)
			selected.moveTo(new Position(x, y));
	}

	public void rotateSelectedShape(float angleInDegree) {
		if (selected != null)
			selected.rotate(angleInDegree);
	}

	public void setSelectedShape(Shape selected) {
		this.selected = selected;
	}

	public Shape getSelectedShape() {
		return this.selected;
	}

	public Shape getShape(int tag) {
		Shape s = null;
		for (Shape shape : shapes) {
			if (shape.getTag() == tag) {
				s = shape;
				break;
			}
		}
		return s;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

}
