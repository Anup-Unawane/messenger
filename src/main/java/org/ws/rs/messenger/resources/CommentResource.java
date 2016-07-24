package org.ws.rs.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ws.rs.messenger.model.Comment;
import org.ws.rs.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource 
{
	private CommentService svc = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageID") long messageID)
	{
		return svc.getAllComments(messageID);
	}
	
	@GET
	@Path("/{commentID}")
	public Comment getComment(@PathParam("messageID") long messageID,
							  @PathParam("commentID") long commentID)
	{
		return svc.getComment(messageID, commentID);
	}
	
	@POST
	public Comment addComment(@PathParam("messageID") long messageID, Comment comment)
	{
		return svc.addComment(messageID, comment);
	}
	
	@PUT
	@Path("/{commentID}")
	public Comment updateComment(@PathParam("messageID") long messageID,
								 @PathParam("commentID") long commentID,
								 Comment comment)
	{
		comment.setId(commentID);
		return svc.updateComment(messageID, comment);
	}
	
	
	@DELETE
	@Path("/{commentID}")
	public Comment removeComment(@PathParam("messageID") long messageID,
								 @PathParam("commentID") long commentID)
	{
		return svc.removeComment(messageID, commentID);
	}
}
