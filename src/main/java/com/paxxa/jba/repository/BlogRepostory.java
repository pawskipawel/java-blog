package com.paxxa.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paxxa.jba.entity.Blog;


/*To create repository we need to create interface for each entity with 
name starting from entities name + "Repository" word. 
Interfaces need to extend JpaRepository<Entiti name, Integer for serialization>*/

public interface BlogRepostory extends JpaRepository<Blog, Integer>{

}
