package org.ingram.color.calculator;

import org.ingram.color.space.ColorSpace;

public class TestCalculator implements DistanceCalculator{

	public int timesCalled = 0;
	
	public double percentDistance(ColorSpace space1, ColorSpace space2) {
		return distance(space1, space2) / getMaxDistance() * 100;
	}

	public double distance(ColorSpace space1, ColorSpace space2) {
		return ++timesCalled;
	}

	public double getMaxDistance() {
		return 10;
	}

}
