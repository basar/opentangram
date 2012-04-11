package com.basarc.opentangram.game;

import com.basarc.opentangram.util.Utils;

public class SmallTriangle extends Shape {

	// Initial (or default) vertices
	public static final Position P0 = new Position(0, 2 * Utils.sqrt(2) / 3);
	public static final Position P1 = new Position(-Utils.sqrt(2),
			-Utils.sqrt(2) / 3);
	public static final Position P2 = new Position(Utils.sqrt(2),
			-Utils.sqrt(2) / 3);

	public SmallTriangle() {
		super();
		initialize();
	}

	public SmallTriangle(Position p) {
		this();
		changePosition(p);
	}

	public SmallTriangle(float x, float y) {
		this(new Position(x, y));
	}

	public SmallTriangle(float scale, Position center) {
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
