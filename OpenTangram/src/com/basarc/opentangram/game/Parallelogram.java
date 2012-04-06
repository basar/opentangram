package com.basarc.opentangram.game;

public class Parallelogram extends Shape {

	public Parallelogram() {
		super();
		initialize();
	}

	public Parallelogram(Position p) {
		this();
		changePosition(p);
	}

	public Parallelogram(float x, float y) {
		this(new Position(x, y));
	}

	@Override
	protected void initialize() {
		createVertex(2, 1);
		createVertex(0, 1);
		createVertex(-2, -1);
		createVertex(0, -1);
	}

}
