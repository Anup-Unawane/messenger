package org.ws.rs.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ws.rs.messenger.database.MessengerDatabase;
import org.ws.rs.messenger.model.Comment;
import org.ws.rs.messenger.model.Message;

public class CommentService 
{
	private Map<Long, Message> messages = MessengerDatabase.getMessages();
	
	public List<Comment> getAllComments(long messageID)
	{
		return new ArrayList<Comment>(messages.get(messageID).getComments().values());
	}
	
	public Comment getComment(long messageID, long commentID)
	{
		return messages.get(messageID).getComments().get(commentID);
	}
	
	public Comment addComment(long messageID, Comment comment)
	{
		Map<Long, Comment> comments = messages.get(messageID).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageID, Comment comment)
	{
		Map<Long, Comment> comments = messages.get(messageID).getComments();
		if(comment.getId()<= 0)
			return null;
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageID, long commentID)
	{
		Map<Long, Comment> comments = messages.get(messageID).getComments();
		return comments.remove(commentID);
	}
}
