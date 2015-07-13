package org.ingram.color.calculator;

import org.ingram.color.space.ColorSpace;
import org.ingram.color.space.LabColorSpace;

/**
 * Color distance calculator based on perception to the human eye.
 * CIE76 is the original calculation, but modern versions are more precise.
 * (This simply converts the colors to the CIE*Lab scheme and takes the Euclidean distance.)
 *
 */
public class Cie76Calculator implements DistanceCalculator {

	/* L min: 0, max: 100
	 * A min: -128, max: 128
	 * B min: -128, max: 128
	 * 
	 * max distance = ((100-0)^2 + (128 + 128)^2 + (128 + 128)^2)^.5
	 */
	private static final double maxDistance = 375.5955;

	public double getMaxDistance() {
		return maxDistance;
	}

	public double percentDistance(ColorSpace first, ColorSpace second){
		return (distance(first, second) / getMaxDistance()) * 100;
	}
	
	public double distance(ColorSpace first, ColorSpace second) {
		LabColorSpace firstLab = toLab(first);
		LabColorSpace secondLab = toLab(second);
		
		double l = differenceSquared(firstLab.getL(), secondLab.getL());
		double a = differenceSquared(firstLab.getA(), secondLab.getA());
		double b = differenceSquared(firstLab.getB(), secondLab.getB());
		
		return Math.pow(l + a + b, 0.5);
	}
	
	private LabColorSpace toLab(ColorSpace colorSpace){
		LabColorSpace spaceAsLab = new LabColorSpace();
		colorSpace.populateNewSpace(spaceAsLab);

		return spaceAsLab;
	}
	
	private double differenceSquared(double first, double second){
		return Math.pow(first - second, 2);
	}
}
