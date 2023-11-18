package com.daker.journalApp.controller;


import com.daker.journalApp.entity.JournalEntity;
import com.daker.journalApp.repository.JournalEntryRepository;
import com.daker.journalApp.service.JournalEntryService;
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
@RequestMapping("/api/v2")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    private JournalEntryRepository journalEntryRepository;


    @RequestMapping(value = "/journal")
    public ResponseEntity<?> getAllJournal(){
        try {
            ResponseEntity<?> ResponseEntity;
            return new ResponseEntity<>(journalEntryService.getAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/journal/id/{myId}")
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


    @PostMapping("/journal")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntity myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @DeleteMapping("/journal/id/{myId}")
    public ResponseEntity<?> DeleteJournalById(@PathVariable ObjectId myId ){
        try {
            journalEntryService.deleteById(myId);
            return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/journal/id/{myId}")
    public ResponseEntity<?> UpdateJournalById(@PathVariable ObjectId myId,@RequestBody JournalEntity myEntry){
        try {
            JournalEntity old = journalEntryService.findById(myId).orElse(null);
            if(old!=null){
                old.setTitle(myEntry.getTitle() !=null && !myEntry.getTitle().equals("")?myEntry.getTitle():old.getTitle());
                old.setContent(myEntry.getContent() !=null && !myEntry.getContent().equals("")?myEntry.getContent():old.getContent());

            }
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

}
