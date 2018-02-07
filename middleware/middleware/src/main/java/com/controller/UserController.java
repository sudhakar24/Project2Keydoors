package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Dao.UserDao;
import com.model.ErrorClazz;
import com.model.User;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public ResponseEntity<?> registeration(@RequestBody User user) {
		try {
			if (!userDao.isUsernameValid(user.getUsername())) {
				ErrorClazz error = new ErrorClazz(2, "Username already exists..please choose different username");
				return new ResponseEntity<ErrorClazz>(error, HttpStatus.CONFLICT);
			}
			if (!userDao.isEmailValid(user.getEmail())) {
				ErrorClazz error = new ErrorClazz(3, "EmailId already exists..please enter differnt emailid");
				return new ResponseEntity<ErrorClazz>(error, HttpStatus.CONFLICT);
			}
			userDao.registerUser(user);
		} catch (Exception e) {
			ErrorClazz error = new ErrorClazz(1, "Unable to register user details");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User validUser = userDao.login(user);
		if (validUser == null) {
			ErrorClazz errorClazz = new ErrorClazz(4, "Invalid username/password");
			return new ResponseEntity<ErrorClazz>(errorClazz, HttpStatus.UNAUTHORIZED);

		} else {
			validUser.setOnline(true);
			session.setAttribute("username", validUser.getUsername());
			userDao.updateUser(validUser);
			return new ResponseEntity<User>(validUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unautherized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);

		}
		User user = userDao.getUserByUsername(username);
		user.setOnline(false);
		userDao.updateUser(user);
		session.removeAttribute("username");
		session.invalidate();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);

		}
		User user = userDao.getUserByUsername(username);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/edituseruser", method = RequestMethod.PUT)
	public ResponseEntity<?> editUserProfile(@RequestBody User user, HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			ErrorClazz error = new ErrorClazz(5, "Unautherized access");
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.UNAUTHORIZED);
		}
		try {
			userDao.updateUser(user);
		} catch (Exception e) {
			ErrorClazz error = new ErrorClazz(6, e.getMessage());
			return new ResponseEntity<ErrorClazz>(error, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
