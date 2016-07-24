package org.ws.rs.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.ws.rs.messenger.model.Comment;
import org.ws.rs.messenger.model.Message;
import org.ws.rs.messenger.model.Profile;

/** this is just placeholder class. Actual database access implementation may vary */
public class MessengerDatabase {
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();

	static
	{//initialize database with sample data
		Message m1 = new Message(1L, "Hello World!!!", "Anup");
		Message m2 = new Message(2L, "Hello Jersey!!!", "Anup");
		
		messages.put(1L, m1);
		messages.put(2L, m2);
		
		m1.getComments().put(1L, new Comment(1L, "Hello World comment!!!", "Neha"));
		m1.getComments().put(2L, new Comment(2L, "Hello World comment222!!!", "ABC"));
		m2.getComments().put(1L, new Comment(1L, "Hello Jersey comment!!!", "Neha"));
		m2.getComments().put(2L, new Comment(2L, "Hello Jersey comment222!!!", "CBA"));
	}
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
	
}
