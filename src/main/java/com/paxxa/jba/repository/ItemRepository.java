package com.paxxa.jba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paxxa.jba.entity.Blog;
import com.paxxa.jba.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	List<Item> findByBlog(Blog blog);
}
