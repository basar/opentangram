package com.basarc.opentangram.game;

public class BigTriangle extends Shape {

	public BigTriangle() {
		super();
		initialize();
	}

	public BigTriangle(Position p) {
		this();
		changePosition(p);
	}

	public BigTriangle(double x, double y) {
		this(new Position(x, y));
	}

	@Override
	protected void initialize() {
		vertices.add(new Position(2, 0));
		vertices.add(new Position(-2, 0));
		vertices.add(new Position(0, -2));
	}

}
