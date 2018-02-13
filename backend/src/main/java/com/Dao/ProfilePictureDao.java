package com.Dao;

import com.model.ProfilePicture;

public interface ProfilePictureDao {
void saveorupdateProfilePicture(ProfilePicture profilepicture);
ProfilePicture getProfilePicture(String username);
}
