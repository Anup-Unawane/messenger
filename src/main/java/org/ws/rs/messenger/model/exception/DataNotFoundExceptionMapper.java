package org.ws.rs.messenger.model.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ws.rs.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage msg = new ErrorMessage(ex.getMessage(), 404, "JAVA Docs URL");
		return Response.status(Status.NOT_FOUND).entity(msg).build();
	}

}
