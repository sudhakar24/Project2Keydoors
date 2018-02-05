package com.Dao;

import com.model.BlogPost;
import com.model.BlogPostLikes;
import com.model.User;

public interface BlogPostLikesDao {
	BlogPostLikes userLikes(BlogPost blogPost,User user);
	BlogPost updateLikes(BlogPost blogPost, User user);
}
