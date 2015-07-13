package org.ingram.color.util;

import java.util.ArrayList;
import java.util.List;

import org.ingram.color.Color;
import org.ingram.color.ColorList;
import org.ingram.color.entity.ColorEntity;
import org.ingram.color.entity.ColorListEntity;
import org.ingram.color.space.RgbColorSpace;
import org.ingram.color.web.ColorDistanceMatch;
import org.ingram.color.web.ColorPretty;

public class TestColorListGenerator {

	public static ColorList getTestList(){
		ColorListEntity testList = new ColorListEntity("Test List");
		
		ArrayList<ColorEntity> colors = new ArrayList<ColorEntity>();
		
		colors.add( new Color( "Mahogany", new RgbColorSpace(205, 74, 74)) );
		colors.add( new Color( "Fuzzy Wuzzy Brown", new RgbColorSpace(204, 102, 102)) );
		colors.add( new Color( "Chestnut", new RgbColorSpace(188,93,88)) );
		colors.add( new Color( "Red Orange", new RgbColorSpace(255,83,73)) );
		
		testList.setColors(colors);
		
		return new ColorList(testList);
	}
	
	public static List<ColorPretty> getTestPrettyColorsSorted(){
		List<ColorPretty> prettyColors = new ArrayList<ColorPretty>();
		
		prettyColors.add(new ColorPretty("Chestnut", new RgbColorSpace(188,93,88)));
		prettyColors.add(new ColorPretty("Fuzzy Wuzzy Brown", new RgbColorSpace(204, 102, 102)));
		prettyColors.add(new ColorPretty("Mahogany", new RgbColorSpace(205, 74, 74)));
		prettyColors.add(new ColorPretty("Red Orange", new RgbColorSpace(255,83,73)));
		
		return prettyColors;
	}
	
	public static List<ColorDistanceMatch> getColorDistanceMatches(int firstX){
		List<ColorDistanceMatch> matches = new ArrayList<ColorDistanceMatch>();
		
		List<ColorEntity> colors = getTestList().getColors();
		
		for(int i = 0; i < firstX && i < colors.size(); ++i){
			Color color = new Color(colors.get(i));
			matches.add(new ColorDistanceMatch(color.getName(), 10.0 * (i+1), color.getColorSpace()));
		}
		
		return matches;
	}
}
