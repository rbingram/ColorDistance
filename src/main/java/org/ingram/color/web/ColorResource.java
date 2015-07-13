package org.ingram.color.web;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.ingram.color.Color;
import org.ingram.color.space.RgbColorSpace;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ColorResource extends ServerResource{

	@Get
	public String getColors(){
		return new ColorListResource().getList();
	}
	
	private static final String NAME = "name";
	private static final String RGB = "rgb";
	
	@Post
	public String addColor(Representation entity) {
		Form form = new Form(entity);
		
		String name = form.getFirstValue(NAME);
		String rgb = form.getFirstValue(RGB);
		
		Color color = Color.fetchByName(name);
		ColorPretty prettyColor = null;
		if(color == null){
			RgbColorSpace rgbSpace = RgbColorSpace.fromDecimalString(rgb);
			if(rgbSpace != null){
				color = new Color(name, rgbSpace);
				color.save();
				
				prettyColor = new ColorPretty(color, true);
			}
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(prettyColor);
	}
	
	private static final String IDS = "id";
	private static final String DELETED_IDS = "deletedIds";
	private static final String MISSING_IDS = "missingIds";
	private static final String MALFORMED_IDS = "malformedIds";
	
	@Delete
	public String removeColor(Representation entity){
		Form form = new Form(entity);
		
		String ids = form.getValues(IDS);
		Set<Integer> deletedIds = new HashSet<Integer>();
		Set<Integer> missingIds = new HashSet<Integer>();
		Set<String> malformedIds = new HashSet<String>();
		if(ids != null){
			String[] idsSplit = ids.split(",");
			for(int i = 0; i < idsSplit.length; ++i){
				try{
					int idAsInt = Integer.parseInt(idsSplit[i].trim());
					if(Color.deleteById(idAsInt)){
						deletedIds.add(idAsInt);
					}else{
						missingIds.add(idAsInt);
					}
				}catch(NumberFormatException nfe){
					malformedIds.add(idsSplit[i]);
				}
			}
		}
		
		if(deletedIds.isEmpty() && !malformedIds.isEmpty()){
			throw new ColorValidationException("Ids must be integers. Unable to remove colors ids " + ids);
		}
		
		JsonObject json = new JsonObject();
		json.addProperty(DELETED_IDS, StringUtils.join(deletedIds.toArray(new Integer[0]), ", "));
		json.addProperty(MISSING_IDS, StringUtils.join(missingIds.toArray(new Integer[0]), ","));
		json.addProperty(MALFORMED_IDS, StringUtils.join(malformedIds.toArray(new String[0]), ","));
		return json.toString();
	}
}
