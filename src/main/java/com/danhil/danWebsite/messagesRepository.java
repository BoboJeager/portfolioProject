package com.danhil.danWebsite;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface messagesRepository extends MongoRepository<messages, ObjectId> {
}
