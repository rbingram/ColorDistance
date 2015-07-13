package org.ingram.color.calculator;


import org.ingram.color.util.TestColorGenerator;
import org.junit.Assert;
import org.junit.Test;

public class Cie76CalculatorTest {

	//results taken from http://colormine.org/delta-e-calculator/
	private static final double BLACK_WHITE = 100;
	private static final double BLUE_PURPLEISH = 87.6631;
	private static final double BLUE_COBALTBLUE = 75.191;
	
	private static final double MAX_DISTANCE = new Cie76Calculator().getMaxDistance();
	
	private static final double BLACK_WHITE_PERCENT = BLACK_WHITE / MAX_DISTANCE * 100; 
	private static final double BLUE_PURPLEISH_PERCENT = BLUE_PURPLEISH / MAX_DISTANCE * 100;
	private static final double BLUE_COBALTBLUE_PERCENT = BLUE_COBALTBLUE / MAX_DISTANCE * 100;
	
	@Test
	public void distance_whiteAndBlack(){
		Cie76Calculator calc = new Cie76Calculator();
		
		double deltaE = calc.distance(TestColorGenerator.white.getRgb(), TestColorGenerator.black.getRgb());
		Assert.assertEquals(BLACK_WHITE, deltaE, .001);
	}
	
	@Test
	public void distance_blueEqualsBlue(){
		Cie76Calculator calc = new Cie76Calculator();
		
		double deltaE = calc.distance(TestColorGenerator.blue.getRgb(), TestColorGenerator.blue.getRgb());
		Assert.assertEquals(0.0, deltaE, 0.0);
	}
	
	@Test
	public void distance_transitivity(){
		Cie76Calculator calc = new Cie76Calculator();
		
		double deltaE = calc.distance(TestColorGenerator.blue.getRgb(), TestColorGenerator.purpleish.getRgb());
		Assert.assertEquals(BLUE_PURPLEISH, deltaE, .001);
		
		deltaE = calc.distance(TestColorGenerator.purpleish.getRgb(), TestColorGenerator.blue.getRgb());
		Assert.assertEquals(BLUE_PURPLEISH, deltaE, .001);
	}
	
	@Test
	public void distance_colorSpaces(){
		Cie76Calculator calc = new Cie76Calculator();
		
		double deltaE = calc.distance(TestColorGenerator.blue.getRgb(), TestColorGenerator.cobaltBlue.getRgb());
		Assert.assertEquals(BLUE_COBALTBLUE, deltaE, .001);
		
		deltaE = calc.distance(TestColorGenerator.blue.getXyz(), TestColorGenerator.cobaltBlue.getLab());
		Assert.assertEquals(BLUE_COBALTBLUE, deltaE, .001);
	}
	
	@Test
	public void percentDistance(){
		Cie76Calculator calc = new Cie76Calculator();
		
		double percent = calc.percentDistance(TestColorGenerator.blue.getRgb(), TestColorGenerator.cobaltBlue.getRgb());
		Assert.assertEquals(BLUE_COBALTBLUE_PERCENT, percent, .001);
		
		percent = calc.percentDistance(TestColorGenerator.white.getRgb(), TestColorGenerator.black.getRgb());
		Assert.assertEquals(BLACK_WHITE_PERCENT, percent, .001);
		
		percent = calc.percentDistance(TestColorGenerator.blue.getLab(), TestColorGenerator.purpleish.getLab());
		Assert.assertEquals(BLUE_PURPLEISH_PERCENT, percent, .001);
	}

}
