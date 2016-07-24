package org.ws.rs.messenger.model.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ws.rs.messenger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable th) 
	{
		ErrorMessage msg = new ErrorMessage(th.getMessage(), 500, "JAVA Docs URL");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();
	}

}
