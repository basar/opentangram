package com.basarc.opentangram.game;

import com.basarc.opentangram.util.Utils;

public class BigTriangle extends Shape {

	// Initial (or default) vertices
	public static final Position P0 = new Position(0, 4 * Utils.sqrt(2) / 3);
	public static final Position P1 = new Position(-2 * Utils.sqrt(2), -2
			* Utils.sqrt(2) / 3);
	public static final Position P2 = new Position(2 * Utils.sqrt(2), -2
			* Utils.sqrt(2) / 3);

	public BigTriangle() {
		super();
		initialize();
	}

	public BigTriangle(Position p) {
		this();
		changePosition(p);
	}

	public BigTriangle(float x, float y) {
		this(new Position(x, y));
	}

	public BigTriangle(float scale, Position center) {
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
