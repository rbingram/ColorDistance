package org.ingram.color.space;

import org.ingram.color.util.TestColor;
import org.ingram.color.util.TestColorGenerator;
import org.ingram.color.util.Validation;
import org.junit.Test;

public class LabColorSpaceTest {

	@Test
	public void toXyz(){
		for(TestColor color: TestColorGenerator.testColors){
			Validation.confirm(color.getXyz(), color.getLab().toXyz());
		}
	}
	
	@Test
	public void fromXyz(){
		for(TestColor color: TestColorGenerator.testColors){
			LabColorSpace lab = new LabColorSpace();
			lab.fromXyz(color.getXyz());
			Validation.confirm(color.getLab(), lab);
		}
	}
	
}
