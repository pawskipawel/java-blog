package com.paxxa.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paxxa.jba.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
