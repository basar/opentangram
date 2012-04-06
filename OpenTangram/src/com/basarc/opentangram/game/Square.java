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

	public Square(float x, float y) {
		this(new Position(x, y));
	}

	@Override
	protected void initialize() {
		createVertex(1, 1);
		createVertex(-1, 1);
		createVertex(-1, -1);
		createVertex(1, -1);
	}

}
