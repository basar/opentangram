package com.basarc.opentangram.game;

import java.util.ArrayList;

/**
 * 
 * @author basar
 * 
 */
public class PuzzleBoard {

	private static PuzzleBoard instance = null;

	private float scale;

	private float width;

	private float height;

	private ArrayList<Shape> shapes;

	private Shape selected;

	private final int SHAPE_COUNT = 7;

	private PuzzleBoard() {
		shapes = new ArrayList<Shape>();
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

		createShapes();

	}

	private void createShapes() {

		scale = height / 20;
		Square sqr = new Square(scale, new Position(scale, scale));
		BigTriangle bt1 = new BigTriangle(scale, new Position(scale, scale * 5));
		BigTriangle bt2 = new BigTriangle(scale,
				new Position(scale, scale * 10));

		bt2.rotate(90);
		sqr.scale(0.5f);
		bt1.scale(0.5f);

		shapes.add(sqr);
		shapes.add(bt1);
		shapes.add(bt2);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

}
