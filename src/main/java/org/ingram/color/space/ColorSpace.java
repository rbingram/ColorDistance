package org.ingram.color.space;

import org.apache.commons.lang3.StringUtils;
import org.ingram.color.Color;


public abstract class ColorSpace {

	public abstract void fromXyz(XyzColorSpace xyz);
	public abstract XyzColorSpace toXyz();
	
	public void populateNewSpace(ColorSpace newSpace){
		newSpace.fromXyz(toXyz());
	}
	
	public static ColorSpace fromName(String name){
		ColorSpace space = null;
		
		if(!StringUtils.isBlank(name)){
			Color color = Color.fetchByName(name);
		
			if(color != null){
				space = color.getColorSpace();
			}
		}
		
		return space;
	}
}
