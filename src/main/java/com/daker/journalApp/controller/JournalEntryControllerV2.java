package com.daker.journalApp.controller;


import com.daker.journalApp.entity.JournalEntity;
import com.daker.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/journal")
    public List<JournalEntity> getAllJournal(){
        return journalEntryService.getAll();
    }

    @GetMapping("/journal/id/{myId}")
    public Optional<JournalEntity> getJournalById(@PathVariable ObjectId myId ){
        return journalEntryService.findById(myId);
    }


    @PostMapping("/journal")
    public JournalEntity createEntry(@RequestBody JournalEntity myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }
    @DeleteMapping("/journal/id/{myId}")
    public boolean DeleteJournalById(@PathVariable ObjectId myId ){
         journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("/journal/id/{myId}")
    public JournalEntity UpdateJournalById(@PathVariable ObjectId myId,@RequestBody JournalEntity myEntry){

        JournalEntity old = journalEntryService.findById(myId).orElse(null);
        if(old!=null){
            old.setTitle(myEntry.getTitle() !=null && !myEntry.getTitle().equals("")?myEntry.getTitle():old.getTitle());
            old.setContent(myEntry.getContent() !=null && !myEntry.getContent().equals("")?myEntry.getContent():old.getContent());

        }
        journalEntryService.saveEntry(old);
        return old;

    }

}
