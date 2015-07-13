package org.ingram.color.space;

public class LabColorSpace extends ColorSpace {
	private double l = -1.0;
	private double a = -1.0;
	private double b = -1.0;
	
	public LabColorSpace(){
		
	}
	
	public LabColorSpace(double l, double a, double b){
		this.l =l;
		this.a = a;
		this.b = b;
	}
	
	
	
	@Override
	public void fromXyz(XyzColorSpace xyz){
		double x = translateFromXyz(xyz.getX() / XyzColorSpace.REF_X);
		double y = translateFromXyz(xyz.getY() / XyzColorSpace.REF_Y);
		double z = translateFromXyz(xyz.getZ() / XyzColorSpace.REF_Z);
		
		setL((116 * y) - 16);
		setA(500 * (x - y));
		setB(200 * (y - z));
	}
	
	@Override
	public XyzColorSpace toXyz() {
		double y = (l + 16) / 116.0;
		double x = (a / 500.0) + y;
		double z = y - (b / 200.0);

		x = translateToXyz(x);
		y = translateToXyz(y);
		z = translateToXyz(z);
		
		x *= XyzColorSpace.REF_X;
		y *= XyzColorSpace.REF_Y;
		z *= XyzColorSpace.REF_Z;
		
		return new XyzColorSpace(x, y, z);
	}
	
	private double translateToXyz(double coord){
		double coordCubed = Math.pow(coord, 3);
		
		if(coordCubed > 0.008856 ){
			coord = coordCubed;
		}else{
			coord = (coord - (16.0 / 116.0)) / 7.787;
		}
		
		return coord;
	}
	
	
	private double translateFromXyz(double coord){
		if(coord > 0.008856){
			coord = Math.pow(coord, 1/3.0);
		}else{
			coord = (7.787 * coord) + (16 / 116.0);
		}
		
		return coord;
	}
	
	
	private void setL(double newL){
		l = newL;
	}
	
	private void setA(double newA){
		a = newA;
	}
	
	private void setB(double newB){
		b = newB;
	}

	public double getL(){
		return l;
	}
	
	public double getA(){
		return a;
	}
	
	public double getB(){
		return b;
	}

	@Override
	public String toString() {
		return "LabColorSpace [l=" + l + ", a=" + a + ", b=" + b + "]";
	}
	
	
}
