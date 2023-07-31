package com.sports.badmintonclub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.badmintonclub.models.User;

//JPA-Repository for Accessing the USer Entity
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName); 
}

