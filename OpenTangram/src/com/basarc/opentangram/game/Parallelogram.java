package com.basarc.opentangram.game;

public class Parallelogram extends Shape {

	// Initial (or default) vertices
	public static final Position P0 = new Position(2, 1);
	public static final Position P1 = new Position(0, 1);
	public static final Position P2 = new Position(-2, -1);
	public static final Position P3 = new Position(0, -1);

	public Parallelogram() {
		super();
		initialize();
	}

	public Parallelogram(Position center) {
		this();
		changePosition(center);
	}

	public Parallelogram(float x, float y) {
		this(new Position(x, y));
	}

	public Parallelogram(float scale, Position center) {
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
