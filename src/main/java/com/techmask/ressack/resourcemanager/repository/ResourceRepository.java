package com.techmask.ressack.resourcemanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.techmask.ressack.resourcemanager.domain.Resource;

public interface ResourceRepository extends MongoRepository<Resource, String>{

}
