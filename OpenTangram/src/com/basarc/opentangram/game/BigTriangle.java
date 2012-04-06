package com.basarc.opentangram.game;

import com.basarc.opentangram.util.Utils;

public class BigTriangle extends Shape {

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

	@Override
	protected void initialize() {
		createVertex(2 * Utils.sqrt(2), 0);
		createVertex(-2 * Utils.sqrt(2), 0);
		createVertex(0, -2 * Utils.sqrt(2));
	}

}
