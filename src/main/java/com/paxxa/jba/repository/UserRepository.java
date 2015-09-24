package com.paxxa.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paxxa.jba.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
