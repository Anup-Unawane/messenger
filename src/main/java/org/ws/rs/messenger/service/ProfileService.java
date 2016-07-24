package org.ws.rs.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ws.rs.messenger.database.MessengerDatabase;
import org.ws.rs.messenger.model.Profile;

public class ProfileService 
{
	private Map<String, Profile> profiles = MessengerDatabase.getProfiles();
	
	public ProfileService()
	{
		profiles.put("anup", new Profile(1, "anup", "Anup", "Unawane"));
	}

	public List<Profile> getAllProfiles()
	{
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String id)
	{
		return profiles.get(id);
	}
	
	public Profile addProfile(Profile msg)
	{
		msg.setId(profiles.size()+1);
		profiles.put(msg.getProfileName(), msg);
		return msg;
	}
	
	public Profile updateProfile(Profile msg)
	{
		if(! profiles.containsKey(msg.getProfileName()))
			return null;
		
		msg.setId(profiles.get(msg.getProfileName()).getId());
		profiles.put(msg.getProfileName(), msg);
		return msg;
	}
	
	public Profile removeProfile(String profileName)
	{
		return profiles.remove(profileName);
	}
}
