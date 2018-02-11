package com.Dao;

import java.util.List;

import com.model.Friend;
import com.model.User;

public interface FriendDao {
	List<User> suggestedUserList(String username);

	void friendRequest(Friend friend);// insert into friend

	List<Friend> pendingRequests(String username);

	void updatePendingRequest(Friend friend);

	List<User> listOfFriends(String username);

	
}
