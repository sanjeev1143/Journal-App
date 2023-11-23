package com.daker.journalApp.controller;


import com.daker.journalApp.entity.JournalEntity;
import com.daker.journalApp.entity.User;
import com.daker.journalApp.repository.JournalEntryRepository;
import com.daker.journalApp.service.JournalEntryService;
import com.daker.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserEntryService userEntryService;


    @RequestMapping(value = "/{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){
        try {
            User user= userEntryService.getUserByUsername(username);
            List<JournalEntity> all = user.getJournalEntities();
            if(all!=null&& !all.isEmpty())
                return new ResponseEntity<>(all,HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJournalById(@PathVariable ObjectId myId ){
        try {
            Optional<JournalEntity> journalEntry = journalEntryService.findById(myId);
            if(journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntity myEntry,@PathVariable String username){
        try {


            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @DeleteMapping("/id/{username}/{myId}")
    public ResponseEntity<?> DeleteJournalById(@PathVariable ObjectId myId ,@PathVariable String username ){
        try {
            journalEntryService.deleteById(myId,username);
            return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/id/{username}/{myId}")
    public ResponseEntity<?> UpdateJournalById(@PathVariable ObjectId myId,@PathVariable String username,@RequestBody JournalEntity myEntry){
        try {

            journalEntryService.updateById(myId,username,myEntry);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

}
