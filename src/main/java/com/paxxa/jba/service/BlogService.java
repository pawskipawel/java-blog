package com.paxxa.jba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paxxa.jba.entity.Blog;
import com.paxxa.jba.entity.User;
import com.paxxa.jba.repository.BlogRepostory;
import com.paxxa.jba.repository.UserRepository;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepostory blogRepostory;
	
	@Autowired
	private UserRepository userRepository;

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepostory.save(blog);
	}
	
	

}
