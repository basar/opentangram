package com.basarc.opentangram.game;

public class STriangle extends Shape {

	public STriangle() {
		super();
		initialize();
	}

	@Override
	public void initialize() {
		center = new Position(0, 0);
	}
}
