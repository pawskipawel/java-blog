package com.paxxa.jba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ADMIN')")
	public void deleteBlog(@P("blog")Blog blog) {
		blogRepostory.delete(blog);
	}

	public Blog findOne(int id) {
		return blogRepostory.findOne(id);

	}
	
	

}
