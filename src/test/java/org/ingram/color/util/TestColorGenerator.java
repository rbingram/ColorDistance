package org.ingram.color.util;

import java.util.ArrayList;
import java.util.Collection;

import org.ingram.color.space.LabColorSpace;
import org.ingram.color.space.RgbColorSpace;
import org.ingram.color.space.XyzColorSpace;

public class TestColorGenerator {
	//Values for formats other than RGB are taken from the calculator at http://www.easyrgb.com/index.php.
	
	public static final TestColor white = new TestColor(
			"FFFFFF",
			new RgbColorSpace(255, 255, 255),
			new LabColorSpace(100.000, 0.005, -0.010),
			new XyzColorSpace(95.050, 100.000, 108.900));
	
	public static final TestColor black = new TestColor(
			"000000",
			new RgbColorSpace(0, 0, 0),
			new LabColorSpace(0, 0, 0),
			new XyzColorSpace(0, 0, 0));
	
	public static final TestColor blue = new TestColor(
			"0000FF",
			new RgbColorSpace(0, 0, 255),
			new LabColorSpace(32.303, 79.197, -107.864),
			new XyzColorSpace(18.050, 7.220, 95.050));

	public static final TestColor cobaltBlue = new TestColor(
			"0047AB",
			new RgbColorSpace(0, 71, 171),
			new LabColorSpace(32.803, 22.526, -58.448),
			new XyzColorSpace(9.604, 7.447, 39.459));
	
	public static final TestColor purpleish = new TestColor(
			"380350",
			new RgbColorSpace(56, 3, 80),
			new LabColorSpace(12.513, 37.047, -33.590),
			new XyzColorSpace(3.111, 1.485, 7.712));
	
	public static final Collection<TestColor> testColors = generateTestColors();
	
	private static Collection<TestColor> generateTestColors(){
		Collection<TestColor> col = new ArrayList<TestColor>();
		
		col.add(white);
		col.add(black);
		col.add(blue);
		col.add(cobaltBlue);
		col.add(purpleish);
		
		return col;
	}
}
