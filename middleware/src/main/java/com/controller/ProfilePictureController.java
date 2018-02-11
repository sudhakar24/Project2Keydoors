package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.Dao.ProfilePictureDao;
import com.model.ErrorClazz;
import com.model.ProfilePicture;

@Controller
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilepicturedao;
	
	@RequestMapping(value="/uploadProfilepic", method=RequestMethod.POST)
	public ResponseEntity<?> uploadprofilepic(@RequestParam CommonsMultipartFile image,HttpSession session){
		String username=(String) session.getAttribute("username");
		if(username==null){
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		ProfilePicture profilepicture=new ProfilePicture();
		profilepicture.setUsername(username);
		profilepicture.setImage(image.getBytes());
		profilepicturedao.uploadprofilepicture(profilepicture);
		return new ResponseEntity<ProfilePicture>(profilepicture,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getProfilepic/{username}", method=RequestMethod.GET)
	public  @ResponseBody byte[] getprofilepic(@PathVariable String username,HttpSession session){
		String loginId=(String) session.getAttribute("username");
		if(loginId==null)
			return null;
		ProfilePicture profilepicture=profilepicturedao.getProfilePicture(username);
		if(profilepicture==null)
			return null;
		else
			return profilepicture.getImage();
	}
	
}
