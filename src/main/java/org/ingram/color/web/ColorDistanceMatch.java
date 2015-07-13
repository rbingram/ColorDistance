package org.ingram.color.web;

import org.ingram.color.space.ColorSpace;

public class ColorDistanceMatch extends ColorPretty{

	private double delta = 0.0;
	
	public ColorDistanceMatch(String name, double delta, ColorSpace space){
		super(name, space);
		setDelta(delta);
	}

	public double getDelta() {
		return delta;
	}

	protected void setDelta(double delta) {
		this.delta = delta;
	}
}
