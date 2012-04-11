package com.basarc.opentangram.game;

public class MediumTriangle extends Shape {

	// Initial (or default) vertices
	public static final Position P0 = new Position(0, 4f / 3f);
	public static final Position P1 = new Position(-2, -2f / 3f);
	public static final Position P2 = new Position(2, -2f / 3f);

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

	public MediumTriangle(float scale, Position center) {
		this(center);
		scale(scale);
	}

	@Override
	protected void initialize() {
		createVertex(P0);
		createVertex(P1);
		createVertex(P2);
	}

}
