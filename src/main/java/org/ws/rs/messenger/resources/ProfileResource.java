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

import org.ws.rs.messenger.model.Profile;
import org.ws.rs.messenger.service.ProfileService;


@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource 
{
	private ProfileService svc = new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles()
	{
		return svc.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName")String profileName)
	{
		return svc.getProfile(profileName);
	}
	
	@POST
	public Profile addProfile(Profile profile)
	{
		return svc.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName")String profileName, Profile profile)
	{
		profile.setProfileName(profileName);
		return svc.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName")String profileName)
	{
		svc.removeProfile(profileName);
	}
}
