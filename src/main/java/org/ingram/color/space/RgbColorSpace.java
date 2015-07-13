package org.ingram.color.space;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.ingram.color.web.ColorValidationException;

public class RgbColorSpace extends ColorSpace{

	private int r=-1;
	private int g=-1;
	private int b=-1;
	
	public RgbColorSpace(){
		
	}
	
	public RgbColorSpace(int r, int g, int b){
		setR(r);
		setG(g);
		setB(b);
	}
	
	@Override
	public void fromXyz(XyzColorSpace xyz){
		double x = xyz.getX() / 100.0;
		double y = xyz.getY() / 100.0;
		double z = xyz.getZ() / 100.0;
		
		double r = (x *  3.2406) + (y * -1.5372) + (z * -0.4986);
		double g = (x * -0.9689) + (y *  1.8758) + (z *  0.0415);
		double b = (x *  0.0557) + (y * -0.2040) + (z *  1.0570);

		setR(translateFromXyz(r) * MAX_RGB_VAL);
		setG(translateFromXyz(g) * MAX_RGB_VAL);
		setB(translateFromXyz(b) * MAX_RGB_VAL);
	}
	
	private double translateFromXyz(double coord){
		if(coord > 0.0031308 ){
			coord = 1.055 * Math.pow(coord, 1 / 2.4) - 0.055;
		}else{
			coord = 12.92 * coord;
		}
		
		return coord;
	}
	
	@Override
	public XyzColorSpace toXyz(){
		double rPrep = translateToXyz(r);
		double gPrep = translateToXyz(g);
		double bPrep = translateToXyz(b);

		//Observer. = 2°, Illuminant = D65
		
		double x = rPrep * 0.4124 + gPrep * 0.3576 + bPrep * 0.1805;
		double y = rPrep * 0.2126 + gPrep * 0.7152 + bPrep * 0.0722;
		double z = rPrep * 0.0193 + gPrep * 0.1192 + bPrep * 0.9505;
		
		return new XyzColorSpace(x, y, z);
	}

	private static final int MAX_RGB_VAL = 255;
	private double translateToXyz(int coordAsInt){
		double coord = coordAsInt / new Double(MAX_RGB_VAL);
		
		if(coord > 0.04045){
			coord = (coord + 0.055) / 1.055;
			coord = Math.pow(coord, 2.4);
		}else{
			coord = coord / 12.92;
		}
		
		coord = coord * 100;
		
		return coord;
	}

	
	private void setR(int newR){
		r = newR;
	}
	
	private void setR(double newR){
		r = (int)Math.round(newR);
	}
	
	private void setG(int newG){
		g = newG;
	}
	
	private void setG(double newG){
		g = (int)Math.round(newG);
	}
	
	private void setB(int newB){
		b = newB;
	}
	
	private void setB(double newB){
		b = (int)Math.round(newB);
	}
	
	public int getR(){
		return r;
	}
	
	public int getG(){
		return g;
	}
	
	public int getB(){
		return b;
	}

	public String getDecimalString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getR()).append(",").append(getG()).append(",").append(getB());
		return sb.toString();
	}
	
	public String getHexString(){
		return String.format("%02X%02X%02X", getR(), getG(), getB());
	}
	
	public static RgbColorSpace fromDecimalString(String rgb){
		if(rgb == null){
			return null;
		}
		
		RgbColorSpace space = null;
		
		if(!StringUtils.isBlank(rgb)){
			rgb = rgb.trim();
			String[] coords = rgb.split(",");
			if(coords.length != 3){
				throw new ColorValidationException("RGB coordinates must be in r,g,b format.");
			}
			
			int r = coordToInt("R", coords[0]);
			int g = coordToInt("G", coords[1]);
			int b = coordToInt("B", coords[2]);
			
			space = new RgbColorSpace(r, g, b);
		}
		
		return space;
	}
	
	private static int coordToInt(String name, String val){
		int returnVal = -1;
		
		val = val.trim();
		if(!StringUtils.isNumeric(val)){
			throw new ColorValidationException(name + " is set to " + val +". It must be a number between 0 and 255.");
		}
		try{
			returnVal = Integer.parseInt(val);
			if(returnVal < 0 || returnVal > 255){
				throw new ColorValidationException(name + " is set to " + val +". It must be a number between 0 and 255.");
			}
		}catch(NumberFormatException nfe){
			throw new ColorValidationException(name + " is set to " + val +". It must be a number between 0 and 255.", nfe);
		}
		
		return returnVal;
	}
	
	private static final String HEX_REGEX = "^[0-9A-Fa-f]+$";
	
	public static RgbColorSpace fromHexString(String hex){
		if(hex == null){
			return null;
		}
		
		if(hex.startsWith("#")){
			hex = hex.substring(1);
		}
		
		hex = hex.trim();
		
		if(hex.length() != 6){
			throw new ColorValidationException(hex + " is not a valid hex string. Must be 6 characters long (not including '#').");
		}
		
		if(!Pattern.matches(HEX_REGEX, hex)){
			throw new ColorValidationException(hex + " is not a valid hex string. Must match this regex: " + HEX_REGEX);
		}
		
		RgbColorSpace space = null;
		if(!StringUtils.isBlank(hex)){
			hex = "#" + hex;
			java.awt.Color awtColor = java.awt.Color.decode(hex);
			space = new RgbColorSpace(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
		}
		
		return space;
	}
	
	@Override
	public String toString() {
		return "RgbColorSpace [r=" + r + ", g=" + g + ", b=" + b + "]";
	}
}
