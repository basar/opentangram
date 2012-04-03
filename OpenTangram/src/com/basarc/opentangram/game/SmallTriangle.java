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

	public SmallTriangle(double x, double y) {
		this(new Position(x, y));
	}

	@Override
	protected void initialize() {
		vertices.add(new Position(Utils.sqrt(2), 0));
		vertices.add(new Position(0, Utils.sqrt(2)));
		vertices.add(new Position(-Utils.sqrt(2), 0));
	}
}
