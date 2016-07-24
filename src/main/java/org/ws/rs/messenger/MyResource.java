package org.ws.rs.messenger;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/annotations")
    public String getIt(
    					@MatrixParam("param") String matrixParam,
    					@HeaderParam("authSessionID") String headerParam,
    					@CookieParam("cookie1")String cookieParam) 
    {
        return "Received Matrix Param as :" + matrixParam  + "; header param as :" + headerParam + "; Cookei is :" + cookieParam; 
    }
    
    @GET
    @Path("/context")
    public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders header)
    {
    	String path = uriInfo.getAbsolutePath().toString();
    	String cookies = header.getCookies().toString();
    	return "Path: " + path + "; Cookies:" + cookies;
    }
}
