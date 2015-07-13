package org.ingram.color;

import java.util.List;

import org.ingram.color.calculator.TestCalculator;
import org.ingram.color.util.TestColorGenerator;
import org.ingram.color.util.TestColorListGenerator;
import org.ingram.color.util.Validation;
import org.ingram.color.web.ColorDistanceMatch;
import org.ingram.color.web.ColorPretty;
import org.junit.Assert;
import org.junit.Test;

public class ColorListTest{

	@Test
	public void getPrettyColors(){
		ColorList list = TestColorListGenerator.getTestList();
		
		List<ColorPretty> prettyColors = list.getPrettyColors(false);
		List<ColorPretty> expectedPrettyColors = TestColorListGenerator.getTestPrettyColorsSorted();
		
		Assert.assertEquals(prettyColors.size(), expectedPrettyColors.size());
		for(int i = 0; i < prettyColors.size(); ++i){
			Assert.assertEquals(expectedPrettyColors.get(i), prettyColors.get(i));
		}
	}
	
	@Test
	public void findSimilarColors(){
		ColorList list = TestColorListGenerator.getTestList();
		
		List<ColorDistanceMatch> matches = list.findSimilarColors(TestColorGenerator.black.getRgb(), 35, new TestCalculator());
		List<ColorDistanceMatch> expectedMatches = TestColorListGenerator.getColorDistanceMatches(3);
		
		Assert.assertEquals(expectedMatches.size(), matches.size());
		
		for(int i = 0; i < matches.size(); ++i){
			Validation.confirm(expectedMatches.get(i), matches.get(i));
		}
	}
}
