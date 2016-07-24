package org.ws.rs.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.ws.rs.messenger.database.MessengerDatabase;
import org.ws.rs.messenger.model.Comment;
import org.ws.rs.messenger.model.Message;

public class MessageService 
{
	private Map<Long, Message> messages = MessengerDatabase.getMessages();
	
	public MessageService() {
	}
	
	public List<Message> getAllMessages()
	{
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year)
	{
		List<Message> yearMessages = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		for(Message msg : messages.values())
		{
			cal.setTime(msg.getCreated());
			if(cal.get(Calendar.YEAR) == year)
				yearMessages.add(msg);
		}
		return yearMessages;
	}
	
	public List<Message> getAllPagenatedMessages(int start, int size)
	{
		return getAllMessages().subList(start, start + size);
	}
	
	public Message getMessage(Long id)
	{
		return messages.get(id);
	}
	
	public Message addMessage(Message msg)
	{
		msg.setId(messages.size()+1);
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message updateMessage(Message msg)
	{
		if(! messages.containsKey(msg.getId()))
			return null;
		
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message removeMessage(Long id)
	{
		return messages.remove(id);
	}
}
