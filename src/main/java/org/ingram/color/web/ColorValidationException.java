package org.ingram.color.web;

import org.restlet.resource.Status;

@Status(value = 400, serialize = true)
public class ColorValidationException extends RuntimeException{
	
	private static final long serialVersionUID = -3221432539601766579L;

	public ColorValidationException(String message){
		super(message);
	}
	
	public ColorValidationException(String message, Exception e){
		super(message, e);
	}

}
