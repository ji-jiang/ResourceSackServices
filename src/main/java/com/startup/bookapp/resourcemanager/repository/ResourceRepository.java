package com.startup.bookapp.resourcemanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.startup.bookapp.resourcemanager.domain.Resource;

public interface ResourceRepository extends MongoRepository<Resource, String>{

}
