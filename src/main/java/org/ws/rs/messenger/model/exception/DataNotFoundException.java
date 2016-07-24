package org.ws.rs.messenger.model.exception;

public class DataNotFoundException extends RuntimeException 
{
	private static final long serialVersionUID = 412225364195316496L;
	
	public DataNotFoundException(String message)
	{
		super(message);
	}

}
