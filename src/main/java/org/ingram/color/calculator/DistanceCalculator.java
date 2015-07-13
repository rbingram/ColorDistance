package org.ingram.color.calculator;

import org.ingram.color.space.ColorSpace;

public abstract interface DistanceCalculator {
	public abstract double percentDistance(ColorSpace space1, ColorSpace space2);
	public abstract double distance(ColorSpace space1, ColorSpace space2);
	public abstract double getMaxDistance();
}
