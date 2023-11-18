package com.daker.journalApp.service;

import com.daker.journalApp.entity.JournalEntity;
import com.daker.journalApp.entity.User;
import com.daker.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserEntryService {
    @Autowired
    UserRepository userRepository;
    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public Optional<User> findById(ObjectId id){

        return userRepository.findById(id);
    }


}
