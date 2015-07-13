package org.ingram.color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.ingram.color.calculator.DistanceCalculator;
import org.ingram.color.entity.ColorEntity;
import org.ingram.color.entity.ColorListEntity;
import org.ingram.color.space.ColorSpace;
import org.ingram.color.web.ColorDistanceMatch;
import org.ingram.color.web.ColorPretty;

public class ColorList extends ColorListEntity {

	public ColorList(ColorListEntity colorListEntity){
		super.setColors(colorListEntity.getColors());
		super.setId(colorListEntity.getId());
		super.setName(colorListEntity.getName());
	}
	
	public List<ColorDistanceMatch> findSimilarColors(ColorSpace baseColor, int varianceThresholdAsPercent, DistanceCalculator calculator){
		List<ColorDistanceMatch> similarColors = new ArrayList<ColorDistanceMatch>();
		
		for(ColorEntity colorEntity: super.getColors()){
			Color color = new Color(colorEntity);
			
	    	double percentDistance = calculator.percentDistance(baseColor, color.getColorSpace());
	    	boolean sameColorAsBase = percentDistance < .001;//find similar colors -- not the same color
	    	if(varianceThresholdAsPercent >= percentDistance && !sameColorAsBase){
	    		similarColors.add(new ColorDistanceMatch(color.getName(), percentDistance, color.getColorSpace()));
	    	}
	    }
		
		Collections.sort(similarColors, new Comparator<ColorDistanceMatch>() {
		    public int compare(ColorDistanceMatch left, ColorDistanceMatch right)  {
		    	if(left.getDelta() == right.getDelta()){
		    		return 0;
		    	}else if(left.getDelta() > right.getDelta()){
		    		return 1;
		    	}
	    		return -1;
		    }
		});
		
		return similarColors;
	}
	
	public List<ColorPretty> getPrettyColors(boolean includeIds){
		List<ColorPretty> colors = new ArrayList<ColorPretty>();
		
		for(ColorEntity entity: super.getColors()){
			colors.add(new ColorPretty(new Color(entity), includeIds));
		}
		
		Collections.sort(colors, new Comparator<ColorPretty>() {
		    public int compare(ColorPretty left, ColorPretty right)  {
		    	return left.getName().compareToIgnoreCase(right.getName());
		    }
		});
		
		return colors;
	}
}
