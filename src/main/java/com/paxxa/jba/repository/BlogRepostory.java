package com.paxxa.jba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paxxa.jba.entity.Blog;
import com.paxxa.jba.entity.User;


/*To create repository we need to create interface for each entity with 
name starting from entities name + "Repository" word. 
Interfaces need to extend JpaRepository<Entiti name, Integer for serialization>*/

public interface BlogRepostory extends JpaRepository<Blog, Integer>{
	
	//Spring JPA will auto generate implementation of this method. 
	// User will be taken from the BLOG attribute above.
	List<Blog> findByUser(User user);
}
