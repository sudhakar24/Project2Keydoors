package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Dao.FriendDao;
import com.Dao.UserDao;
import com.model.ErrorClazz;
import com.model.Friend;
import com.model.User;

@Controller
public class FriendController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private FriendDao friendDao;

	@RequestMapping(value = "/suggestedusers", method = RequestMethod.GET)
	public ResponseEntity<?> suggestedusers(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
// String username="sudhakar";
		List<User> suggestedusers = friendDao.suggestedUserList(username);
		return new ResponseEntity<List<User>>(suggestedusers, HttpStatus.OK);
	}

	@RequestMapping(value = "/friendrequest/{toId}", method = RequestMethod.POST)
	public ResponseEntity<?> friendRequest(@PathVariable String toId, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		Friend friend = new Friend();
		friend.setFromId(username);
		friend.setToId(toId);
		friend.setStatus('P');
		friendDao.friendRequest(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@RequestMapping(value = "/pendingrequests", method = RequestMethod.GET)
	public ResponseEntity<?> pendingRequests(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		List<Friend> pendingRequests = friendDao.pendingRequests(username);
		return new ResponseEntity<List<Friend>>(pendingRequests, HttpStatus.OK);

	}

	@RequestMapping(value = "/updatependingrequest", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@RequestBody Friend friend, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		friendDao.updatePendingRequest(friend);// updated status (A/D)
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	/*@RequestMapping(value = "/getuserdetails/{fromId}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserDetails(@PathVariable String fromId, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		User user = userDao.getUserByUsername(fromId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
*/
	@RequestMapping(value = "/getfriends", method = RequestMethod.GET)
	public ResponseEntity<?> getFriendsList(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		
		List<User> friends = friendDao.listOfFriends(username);
		return new ResponseEntity<List<User>>(friends, HttpStatus.OK);
	}

	/*@RequestMapping(value = "/getmutualfriends", method = RequestMethod.PUT)
	// input is List<User> -> suggestedUsers
	public ResponseEntity<?> getMutualFriends(@RequestBody List<User> suggestedUsers, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		Map<String, List<String>> mutualFriends = new HashMap<String, List<String>>();
		for (User u : suggestedUsers) {
			mutualFriends.put(u.getUsername(), friendDao.getMutualFriends(username, u.getUsername()));
			System.out.println(mutualFriends.size());
		}
		System.out.println(mutualFriends);
		return new ResponseEntity<Map<String, List<String>>>(mutualFriends, HttpStatus.OK);
	}

	@RequestMapping(value = "/getmutualfriendsbyfriends", method = RequestMethod.PUT)
	public ResponseEntity<?> getMutualFriendsbyfriends(@RequestBody List<Friend> friends, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		Map<String, List<String>> mutualFriends = new HashMap<String, List<String>>();
		for (Friend f : friends) {
			mutualFriends.put(f.getFromId(), friendDao.getMutualFriends(username, f.getFromId()));
			mutualFriends.put(f.getToId(), friendDao.getMutualFriends(username, f.getToId()));
			System.out.println(mutualFriends.size());
		}
		System.out.println(mutualFriends);
		return new ResponseEntity<Map<String, List<String>>>(mutualFriends, HttpStatus.OK);
	}

	@RequestMapping(value = "/getallmutualfriends/{friend}", method = RequestMethod.PUT)
	public ResponseEntity<?> getallMutualFriend(@PathVariable String friend, HttpSession session) {
		List<User> users = new ArrayList<User>();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		List<String> mutualFriends = friendDao.getMutualFriends(username, friend);
		for (String user : mutualFriends) {
			User user1 = userDao.getUserByUsername(user);
			users.add(user1);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}*/

}
