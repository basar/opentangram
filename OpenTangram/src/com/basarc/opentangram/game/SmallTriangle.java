package com.basarc.opentangram.game;

import com.basarc.opentangram.util.Utils;

public class SmallTriangle extends Shape {

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

	@Override
	protected void initialize() {
		createVertex(Utils.sqrt(2), 0);
		createVertex(-Utils.sqrt(2), 0);
		createVertex(0, -Utils.sqrt(2));
	}
}
