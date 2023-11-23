package com.daker.journalApp.controller;

import com.daker.journalApp.entity.User;
import com.daker.journalApp.service.UserEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserEntryController {

    private final UserEntryService userEntryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user){
        try {
            userEntryService.saveEntry(user);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try {
            List<User> users = userEntryService.getAll();

            return new ResponseEntity<>(userEntryService.getAll(),HttpStatus.OK);



        }catch (Exception e){

            // You might want to throw a custom exception or handle it differently based on your use case.
            throw new RuntimeException("Failed to retrieve users", e);

        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        userEntryService.updateUser(user);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }




}
