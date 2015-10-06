package com.startup.bookapp.usermanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.startup.bookapp.usermanager.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findOneByEmail(String email);
}
