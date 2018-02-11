package com.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Dao.ProfilePictureDao;
import com.model.ProfilePicture;
@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void uploadprofilepicture(ProfilePicture profilepic) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilepic);
	}

	public ProfilePicture getProfilePicture(String username) {
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture profilepic=(ProfilePicture) session.get(ProfilePicture.class, username);
		return profilepic;
	}


}
