package org.ingram.color.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.ingram.color.Color;
import org.ingram.color.ColorList;
import org.ingram.color.calculator.Cie76Calculator;
import org.ingram.color.entity.ColorEntity;
import org.ingram.color.entity.ColorListEntity;
import org.ingram.color.space.ColorSpace;
import org.ingram.color.space.RgbColorSpace;
import org.ingram.color.util.HibernateUtil;
import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SimilarColorsResource extends ServerResource{

	private static final String THRESHOLD = "threshold";
	private static final String COLORID = "colorId";
	private static final String COLORNAME = "colorName";
	private static final String HEX = "hex";
	private static final String RGB = "rgb";
	
	@Get
	public String findSimilarColors(){
		Form form = getRequest().getResourceRef().getQueryAsForm();

		int listId = 1;
		
		String distanceThresholdAsString = form.getFirstValue(THRESHOLD);
		int distanceThreshold = getDistanceThreshold(distanceThresholdAsString);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		ColorList list = null;
		try{
			list = new ColorList( (ColorListEntity) session.load(ColorListEntity.class, listId) );
		}finally{
			session.close();
		}
	    ColorSpace space = getTargetColorSpaceFromRequest(form, session, listId);
	    
	    List<ColorDistanceMatch> matches = list.findSimilarColors(space, distanceThreshold, new Cie76Calculator());
	    
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    return gson.toJson(matches);
	}
	
	private int getDistanceThreshold(String thresholdAsString){
		if(thresholdAsString == null){
			throw new ColorValidationException("Threshold needed in order to find similar colors.");
		}
		
		int threshold = -1;
		
		try{
			threshold = Integer.parseInt(thresholdAsString);
			
			if(threshold < 1 || threshold > 100){
				throw new ColorValidationException("Threshold must be between 1 and 100");
			}
			
		}catch(NumberFormatException nfe){
			throw new ColorValidationException("Unable to parse threshold value of " + thresholdAsString);
		}
		
		return threshold;
	}
	
	private ColorSpace getTargetColorSpaceFromRequest(Form form, Session session, int listId){
		ColorSpace space = null;
		
		String colorId = form.getFirstValue(COLORID);
		space = fromColorId(session, colorId);

		if(space == null){
			String hex = form.getFirstValue(HEX);
			space = RgbColorSpace.fromHexString(hex);;
		}		
		
		if(space == null){
			String rgb = form.getFirstValue(RGB);
			space = RgbColorSpace.fromDecimalString(rgb);
		}
		
		if(space == null){
			String name = form.getFirstValue(COLORNAME);
			space = ColorSpace.fromName(name);
		}
		
		if(space == null){
			throw new ColorValidationException("Unable to create a color space from any input values.");
		}
		
		return space;
	}
	
	private ColorSpace fromColorId(Session session, String colorIdAsString){
		ColorSpace space = null;
		if(!StringUtils.isBlank(colorIdAsString) && StringUtils.isNumeric(colorIdAsString)){
			int colorId = -1;
			try{
				colorId = Integer.parseInt(colorIdAsString.trim());
			}catch(NumberFormatException nfe){
				throw new ColorValidationException(colorIdAsString + " is not a valid number.", nfe);
			}
			
			//TODO: be more defensive instead of hackily relying on try/catch
			try{
				space = new Color( (ColorEntity) session.load(ColorEntity.class, colorId) ).getColorSpace();
			}catch(ObjectNotFoundException e){
				
			}
			
			if(space == null){
				throw new ColorValidationException("Color id " + colorId + " does not exist.");
			}
		}
		return space;
	}

}
