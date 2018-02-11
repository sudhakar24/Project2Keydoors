package com.Dao;


import com.model.ProfilePicture;

public interface ProfilePictureDao {
	void uploadprofilepicture(ProfilePicture profilepic);
	ProfilePicture getProfilePicture(String username);
}
