package com.basarc.opentangram.game;

public class Square extends Shape {

	// Initial (or default) vertices
	public static final Position P0 = new Position(1, 1);
	public static final Position P1 = new Position(-1, 1);
	public static final Position P2 = new Position(-1, -1);
	public static final Position P3 = new Position(1, -1);

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

	public Square(float scale, Position center) {
		this(center);
		scale(scale);
	}

	@Override
	protected void initialize() {
		createVertex(P0);
		createVertex(P1);
		createVertex(P2);
		createVertex(P3);
	}

}
