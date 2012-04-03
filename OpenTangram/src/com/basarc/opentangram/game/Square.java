package com.basarc.opentangram.game;

public class Square extends Shape {

	public Square() {
		super();
		initialize();
	}

	public Square(Position p) {
		this();
		changePosition(p);
	}

	public Square(double x, double y) {
		this(new Position(x, y));
	}

	@Override
	protected void initialize() {
		vertices.add(new Position(1, 1));
		vertices.add(new Position(-1, 1));
		vertices.add(new Position(-1, -1));
		vertices.add(new Position(1, -1));
	}

}
