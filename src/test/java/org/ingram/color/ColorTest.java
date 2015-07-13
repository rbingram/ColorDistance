package org.ingram.color;



import org.ingram.color.entity.ColorEntity;
import org.ingram.color.space.LabColorSpace;
import org.ingram.color.space.RgbColorSpace;
import org.ingram.color.space.XyzColorSpace;
import org.ingram.color.util.TestColor;
import org.ingram.color.util.TestColorGenerator;
import org.ingram.color.util.Validation;
import org.ingram.color.web.ColorValidationException;
import org.junit.Assert;
import org.junit.Test;

public class ColorTest {

	@Test
	public void labToRgb(){
		for(TestColor color: TestColorGenerator.testColors){
			RgbColorSpace converted = new RgbColorSpace();
			color.getLab().populateNewSpace(converted);
			Validation.confirm(color.getRgb(), converted);
		}
	}
	
	@Test
	public void rgbToLab(){
		for(TestColor color: TestColorGenerator.testColors){
			LabColorSpace converted = new LabColorSpace();
			color.getRgb().populateNewSpace(converted);
			Validation.confirm(color.getLab(), converted);
		}

	}
	
	@Test
	public void xyzToXyz(){
		for(TestColor color: TestColorGenerator.testColors){
			XyzColorSpace converted = new XyzColorSpace();
			color.getXyz().populateNewSpace(converted);
			Validation.confirm(color.getXyz(), converted);
		}
	}
	
	private static final String TEST_NAME = "Test name";
	private static final String BAD_TEST_NAME = "Test's name";
	private static final Integer TEST_ID = 100;
	
	@Test
	public void normalConstructor(){
		Color c = new Color(TEST_NAME, TestColorGenerator.blue.getRgb());
		Assert.assertEquals(TEST_NAME, c.getName());
		
		XyzColorSpace savedSpace = new XyzColorSpace(c.getX(), c.getY(), c.getZ());
		Validation.confirm(TestColorGenerator.blue.getXyz(), savedSpace);
	}
	
	@Test(expected=ColorValidationException.class)
	public void normalConstructor_BadCharacter(){
		new Color(BAD_TEST_NAME, TestColorGenerator.blue.getRgb());
	}
	
	@Test
	public void entityConstructor(){
		XyzColorSpace xyz = TestColorGenerator.blue.getXyz();
		Color c = new Color(generateEntity(xyz));
		
		Assert.assertEquals(TEST_NAME, c.getName());
		Assert.assertEquals(TEST_ID, c.getId());
		
		XyzColorSpace xyzSaved = new XyzColorSpace(c.getX(), c.getY(), c.getZ());
		Validation.confirm(xyz, xyzSaved);
	}
	
	@Test
	public void getColorSpace(){
		XyzColorSpace xyz = TestColorGenerator.blue.getXyz();
		Color c = new Color(generateEntity(xyz));
		
		XyzColorSpace xyzGenerated = new XyzColorSpace();
		c.getColorSpace().populateNewSpace(xyzGenerated);
		
		Validation.confirm(xyz, xyzGenerated);
	}
	
	private ColorEntity generateEntity(XyzColorSpace space){
		ColorEntity entity = new ColorEntity(TEST_NAME, space.getX(), space.getY(), space.getZ());
		entity.setId(TEST_ID);
		return entity;
	}

}
