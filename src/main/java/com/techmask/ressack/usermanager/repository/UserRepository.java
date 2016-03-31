package com.techmask.ressack.usermanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techmask.ressack.usermanager.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findOneByEmail(String email);
}
