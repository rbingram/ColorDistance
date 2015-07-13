package org.ingram.color.web;

import org.ingram.color.util.ColorListGenerator;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ColorDistanceApp extends Application {
	
	@Override
	public synchronized Restlet createInboundRoot() {

		ColorListGenerator.seedCrayolaList();
		
		Router router = new Router(getContext());

		//router.attach("/colorList", ColorListResource.class);
		router.attach("/similarColors", SimilarColorsResource.class);
		router.attach("/color", ColorResource.class);
		
		return router;
	}

}
