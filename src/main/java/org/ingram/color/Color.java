package org.ingram.color;

import org.ingram.color.entity.ColorEntity;
import org.ingram.color.space.ColorSpace;
import org.ingram.color.space.XyzColorSpace;

public class Color extends ColorEntity{
	
	public Color(String name, ColorSpace space){
		XyzColorSpace xyz = space.toXyz();
		super.setX(xyz.getX());
		super.setY(xyz.getY());
		super.setZ(xyz.getZ());
		
		super.setName(name);
	}
	
	public Color(ColorEntity colorEntity){
		super.setX(colorEntity.getX());
		super.setY(colorEntity.getY());
		super.setZ(colorEntity.getZ());
		
		super.setName(colorEntity.getName());
		super.setId(colorEntity.getId());
	}
	
	public ColorSpace getColorSpace(){
		return new XyzColorSpace(getX(), getY(), getZ());
	}
	
	@Override
	public void save(){
		new ColorEntity(this).save();
	}
	
	public static Color fetchByName(String name){
		return fetchFromEntity(ColorEntity.fetchByName(name));
	}
	
	public static Color fetchById(int id){
		return fetchFromEntity(ColorEntity.fetchById(id));
	}
	
	private static Color fetchFromEntity(ColorEntity entity){
		Color color = null;
		
		if(entity != null){
			color = new Color(entity);
		}
		
		return color;
	}
}
