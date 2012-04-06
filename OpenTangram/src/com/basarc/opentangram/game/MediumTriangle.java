package com.basarc.opentangram.game;

public class MediumTriangle extends Shape {

	public MediumTriangle() {
		super();
		initialize();
	}

	public MediumTriangle(Position p) {
		this();
		changePosition(p);
	}

	public MediumTriangle(float x, float y) {
		this(new Position(x, y));
	}

	@Override
	protected void initialize() {
		createVertex(2, 0);
		createVertex(-2, 0);
		createVertex(0, -2);
	}

}
