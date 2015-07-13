package org.ingram.color.space;


import org.ingram.color.util.TestColor;
import org.ingram.color.util.TestColorGenerator;
import org.ingram.color.util.Validation;
import org.ingram.color.web.ColorValidationException;
import org.junit.Assert;
import org.junit.Test;

public class RgbColorSpaceTest {

	@Test
	public void toXyz(){
		for(TestColor color: TestColorGenerator.testColors){
			Validation.confirm(color.getXyz(), color.getRgb().toXyz());
		}
	}
	
	@Test
	public void fromXyz(){
		for(TestColor color: TestColorGenerator.testColors){
			RgbColorSpace rgb = new RgbColorSpace();
			rgb.fromXyz(color.getXyz());
			Validation.confirm(color.getRgb(),rgb);
		}
	}
	
	@Test
	public void toDecimalString(){
		for(TestColor color: TestColorGenerator.testColors){
			RgbColorSpace rgb = color.getRgb();
			Assert.assertEquals(rgb.getR() + "," + rgb.getG() + "," + rgb.getB(), rgb.getDecimalString());			
		}
	}
	
	@Test
	public void toHexString(){
		for(TestColor color: TestColorGenerator.testColors){
			RgbColorSpace rgb = color.getRgb();
			Assert.assertEquals(color.getHex(), rgb.getHexString());			
		}
	}
	
	@Test
	public void fromRgbString(){
		Validation.confirm(RgbColorSpace.fromDecimalString("100,50,250"), new RgbColorSpace(100, 50, 250));
		Validation.confirm(RgbColorSpace.fromDecimalString("1,2,3"), new RgbColorSpace(1, 2, 3));
		Validation.confirm(RgbColorSpace.fromDecimalString("0,0,0"), new RgbColorSpace(0, 0, 0));
		Validation.confirm(RgbColorSpace.fromDecimalString("255,255,255"), new RgbColorSpace(255, 255, 255));
	}
	
	@Test(expected=ColorValidationException.class)
	public void fromRgbString_Over(){
		RgbColorSpace.fromDecimalString("100,256,100");
	}
	
	@Test(expected=ColorValidationException.class)
	public void fromRgbString_Under(){
		RgbColorSpace.fromDecimalString("-1,255,100");
	}
	
	@Test(expected=ColorValidationException.class)
	public void fromRgbString_alpha(){
		RgbColorSpace.fromDecimalString("-1,a,100");
	}
	
	@Test(expected=ColorValidationException.class)
	public void fromRgbString_Extra(){
		RgbColorSpace.fromDecimalString("-1,255,100,");
	}
	
	@Test(expected=ColorValidationException.class)
	public void fromRgbString_Short(){
		RgbColorSpace.fromDecimalString("-1,255");
	}

	@Test
	public void fromHexString(){
		Validation.confirm(RgbColorSpace.fromHexString("#000000"), new RgbColorSpace(0, 0, 0));
		Validation.confirm(RgbColorSpace.fromHexString("#FFFFFF"), new RgbColorSpace(255, 255, 255));
		Validation.confirm(RgbColorSpace.fromHexString("#012345"), new RgbColorSpace(1, 35, 69));
	}
	
	@Test(expected=ColorValidationException.class)
	public void fromHexString_Long(){
		RgbColorSpace.fromDecimalString("FFFFFFF");
	}
	
	@Test(expected=ColorValidationException.class)
	public void fromHexString_Short(){
		RgbColorSpace.fromDecimalString("FFFFF");
	}
	
	@Test(expected=ColorValidationException.class)
	public void fromHexString_BadChar(){
		RgbColorSpace.fromDecimalString("FFGFFF");
	}
}
