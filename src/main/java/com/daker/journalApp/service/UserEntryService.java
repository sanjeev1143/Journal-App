package com.daker.journalApp.service;

import com.daker.journalApp.entity.JournalEntity;
import com.daker.journalApp.entity.User;
import com.daker.journalApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserEntryService {

    private final UserRepository userRepository;
    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void updateUser(User user){
        User old = userRepository.findByUsername(user.getUsername());

        if(old!=null){

            old.setPassword(!user.getPassword().isEmpty() ?user.getPassword():old.getPassword());

            userRepository.save(old);
        } else {
            // Handle the case where the user with the specified username is not found
            throw new RuntimeException("User with username " + user.getUsername() + " not found.");
        }

    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);



    }



    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }


}
