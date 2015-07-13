package org.ingram.color.util;



import org.ingram.color.space.LabColorSpace;
import org.ingram.color.space.RgbColorSpace;
import org.ingram.color.space.XyzColorSpace;
import org.ingram.color.web.ColorDistanceMatch;
import org.junit.Assert;

public class Validation {

	public static void confirm(LabColorSpace expected, LabColorSpace actual){
		Assert.assertEquals(expected.getL(), actual.getL(), .01);
		Assert.assertEquals(expected.getA(), actual.getA(), .01);
		Assert.assertEquals(expected.getB(), actual.getB(), .01);
	}
	
	public static void confirm(XyzColorSpace expected, XyzColorSpace actual){
		Assert.assertEquals(expected.getX(), actual.getX(), .01);
		Assert.assertEquals(expected.getY(), actual.getY(), .01);
		Assert.assertEquals(expected.getZ(), actual.getZ(), .01);
	}
	
	public static void confirm(RgbColorSpace expected, RgbColorSpace actual){
		Assert.assertEquals(expected.getR(), actual.getR());
		Assert.assertEquals(expected.getG(), actual.getG());
		Assert.assertEquals(expected.getB(), actual.getB());
	}
	
	public static void confirm(ColorDistanceMatch expected, ColorDistanceMatch actual){
		Assert.assertEquals(expected.getDelta(), actual.getDelta(), .001);
		Assert.assertEquals(expected.getName(), actual.getName());
		Assert.assertEquals(expected.getHexString(), actual.getHexString());
		Assert.assertEquals(expected.getRgbString(), actual.getRgbString());
		Assert.assertEquals(expected.getId(), actual.getId());
	}
	
}
