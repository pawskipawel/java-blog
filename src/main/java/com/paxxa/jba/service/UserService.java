package com.paxxa.jba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paxxa.jba.entity.Blog;
import com.paxxa.jba.entity.Item;
import com.paxxa.jba.entity.User;
import com.paxxa.jba.repository.BlogRepostory;
import com.paxxa.jba.repository.ItemRepository;
import com.paxxa.jba.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepostory blogRepostory;

	@Autowired
	private ItemRepository itemRepository;

	public List<User> findAll() {
		return userRepository.findAll();

	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		User user = findOne(id);
		List<Blog> blogs = blogRepostory.findByUser(user);
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog);
			blog.setItems(items);

		}
		user.setBlogs(blogs);
		return user;
	}

}
