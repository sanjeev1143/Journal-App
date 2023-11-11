package com.daker.journalApp.repository;

import com.daker.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntity, ObjectId> {




}
