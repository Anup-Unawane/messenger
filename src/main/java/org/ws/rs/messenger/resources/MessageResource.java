package org.ws.rs.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.ws.rs.messenger.model.Message;
import org.ws.rs.messenger.model.exception.DataNotFoundException;
import org.ws.rs.messenger.resources.beans.MessageFilterBean;
import org.ws.rs.messenger.service.MessageService;

@Path("/messages")
public class MessageResource 
{
	MessageService svc = new MessageService();
	
	/*Default implementation for get all messages.
	 * Since jersey calls same method for request parameters, queryparam annotation is added in same method
	 * 
	 * @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages()
	{
		return svc.getAllMessages(); 
	}*/
	
	/*
	 * individual params from url can be accessed with this.
	 * now they are accessed using bean params
	 * @QueryParam("year") int year,
	   @QueryParam("start") int start,
	   @QueryParam("size") int size
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean mfBean)
	{
		int year = mfBean.getYear();
		int start = mfBean.getStart();
		int size = mfBean.getSize();
		
		if(year > 0)
			return svc.getAllMessagesForYear(year);
		else if(start > 0 && size > 0)
			return svc.getAllPagenatedMessages(start, size);
		else
			return svc.getAllMessages(); 
	}
	
	@GET
	@Path("/{messageID}")
//	@Produces(MediaType.APPLICATION_JSON)
	//If accepts param set in HTTP Header as test/xml or application/jason respective output will be retun by jax-rs.
	//similar for Consumes annotation also.
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Message getMessage(@PathParam("messageID")long msgId)
	{
		Message msg = svc.getMessage(msgId);
		if(msg == null)
			throw new DataNotFoundException("Message with ID :" + msgId + " not found!!!");
		return msg;
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Message addMessage(Message msg)
//	{
//		return svc.addMessage(msg);
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message msg, @Context UriInfo uriinfo)
	{
		Message newMsg = svc.addMessage(msg);
		URI uri = uriinfo.getAbsolutePathBuilder().path(String.valueOf(newMsg.getId())).build();
		return Response.created(uri).entity(newMsg).build();
	}
	
	@PUT
	@Path("/{messageID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageID")long msgId, Message msg)
	{
		msg.setId(msgId);
		return svc.updateMessage(msg);
	}
	
	@DELETE
	@Path("/{messageID}")
	public void deleteMessage(@PathParam("messageID")long msgId )
	{
		svc.removeMessage(msgId);
	}
	
	/***
	 * Here CommentResource is considered as subresource. absolute path for Comments resource is
	 * PAth for MessageResource + path for getCommentResource API + Path for CommentResource + path for CommentResource APIs
	 */
	@Path("/{messageID}/comments")
	public CommentResource getCommentResource()
	{
		/***
		 * Here we have created a sub resource. JAX-RS will delegate responsibility to handle comments to this resource
		 * Also note No method annotation is 
		 */
		return new CommentResource();
	}
}
