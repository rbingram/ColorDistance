package org.ingram.color.space;

public class XyzColorSpace extends ColorSpace {

	public static final double REF_X = 95.047;
	public static final double REF_Y = 100.000;
	public static final double REF_Z = 108.883;
	
	private double x;
	private double y;
	private double z;
	
	public XyzColorSpace(){
		
	}
	
	public XyzColorSpace(double x, double y, double z){
		setX(x);
		setY(y);
		setZ(z);
	}
	
	@Override
	public XyzColorSpace toXyz() {
		return this;
	}
	
	@Override
	public void fromXyz(XyzColorSpace xyz){
		setX(xyz.getX());
		setY(xyz.getY());
		setZ(xyz.getZ());
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getZ(){
		return z;
	}
	
	private void setX(double newX){
		x = newX;
	}
	
	private void setY(double newY){
		y = newY;
	}
	
	private void setZ(double newZ){
		z = newZ;
	}

	@Override
	public String toString() {
		return "XyzColorSpace [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
