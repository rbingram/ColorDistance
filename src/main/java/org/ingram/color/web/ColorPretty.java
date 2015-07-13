package org.ingram.color.web;

import org.ingram.color.Color;
import org.ingram.color.space.ColorSpace;
import org.ingram.color.space.RgbColorSpace;

public class ColorPretty {

	private String name = "";
	private String rgb = "";
	private String hex = "";
	private Integer id = null;

	public ColorPretty(String name, ColorSpace space){
		setName(name);
		setRgbAndHex(space);
	}
	
	public ColorPretty(Color color, boolean includeId){
		ColorSpace space = color.getColorSpace();
		RgbColorSpace rgbSpace = new RgbColorSpace();
		space.populateNewSpace(rgbSpace);
		
		setName(color.getName());
		if(includeId){
			setId(color.getId());
		}
		setRgbAndHex(rgbSpace);
	}
	
	public void setRgbAndHex(ColorSpace space){
		RgbColorSpace rgbSpace = new RgbColorSpace();
		space.populateNewSpace(rgbSpace);
		
		setRgbString(rgbSpace.getDecimalString());
		setHexString(rgbSpace.getHexString());
	}
	
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getRgbString() {
		return rgb;
	}

	protected void setRgbString(String rgbString) {
		this.rgb = rgbString;
	}

	public String getHexString() {
		return hex;
	}

	protected void setHexString(String hexString) {
		this.hex = hexString;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hex == null) ? 0 : hex.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rgb == null) ? 0 : rgb.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorPretty other = (ColorPretty) obj;
		if (hex == null) {
			if (other.hex != null)
				return false;
		} else if (!hex.equals(other.hex))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rgb == null) {
			if (other.rgb != null)
				return false;
		} else if (!rgb.equals(other.rgb))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ColorPretty [name=" + name + ", rgb=" + rgb + ", hex=" + hex
				+ ", id=" + id + "]";
	}
	
	
}
