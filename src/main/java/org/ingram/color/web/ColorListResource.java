package org.ingram.color.web;

import java.util.List;

import org.hibernate.Session;
import org.ingram.color.ColorList;
import org.ingram.color.entity.ColorListEntity;
import org.ingram.color.util.HibernateUtil;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ColorListResource extends ServerResource{

	@Get
	public String getList(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<ColorPretty> colors = null;
		try{
			colors = new ColorList( (ColorListEntity) session.load(ColorListEntity.class, 1) ).getPrettyColors(true);
		}finally{
			session.close();
		}
	    
		return gson.toJson(colors);
	}
}
