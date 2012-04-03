package com.basarc.opentangram.game;

public class Square extends Shape {

	public Square() {
		super();
		initialize();
	}

	public Square(Position p) {
		this();
		moveTo(p);
	}

	public Square(double x, double y) {
		this(new Position(x, y));
	}

	@Override
	public void initialize() {
		vertices.add(new Position(1, 1));
		vertices.add(new Position(-1, 1));
		vertices.add(new Position(-1, -1));
		vertices.add(new Position(1, -1));
	}

}
