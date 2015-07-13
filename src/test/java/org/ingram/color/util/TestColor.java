package org.ingram.color.util;

import org.ingram.color.space.LabColorSpace;
import org.ingram.color.space.RgbColorSpace;
import org.ingram.color.space.XyzColorSpace;

public class TestColor {

	private String hex = null;
	private RgbColorSpace rgb = null;
	private LabColorSpace lab = null;
	private XyzColorSpace xyz = null;
	
	public TestColor(String hex, RgbColorSpace rgb, LabColorSpace lab, XyzColorSpace xyz){
		setHex(hex);
		setRgb(rgb);
		setLab(lab);
		setXyz(xyz);
	}

	public RgbColorSpace getRgb() {
		return rgb;
	}

	private void setRgb(RgbColorSpace rgb) {
		this.rgb = rgb;
	}

	public LabColorSpace getLab() {
		return lab;
	}

	private void setLab(LabColorSpace lab) {
		this.lab = lab;
	}

	public XyzColorSpace getXyz() {
		return xyz;
	}

	private void setXyz(XyzColorSpace xyz) {
		this.xyz = xyz;
	}
	
	public String getHex(){
		return hex;
	}
	
	public void setHex(String hex){
		this.hex = hex;
	}
	
	
}
