package com.daker.journalApp.controller;


import com.daker.journalApp.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JournalEntryController {

    private HashMap<Long, JournalEntity> journalEntries= new HashMap<>();

    @GetMapping("/journal")
    public List<JournalEntity> getAllJournal(){
        return new ArrayList<>(journalEntries.values());
    }

    @GetMapping("/journal/id/{myId}")
    public  JournalEntity getJournalById(@PathVariable Long myId ){
         return journalEntries.get(myId);
    }
    @PostMapping("/journal")
    public boolean createEntry(@RequestBody JournalEntity myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }
    @DeleteMapping("/journal/id/{myId}")
    public  JournalEntity DeleteJournalById(@PathVariable Long myId ){
        return journalEntries.remove(myId);
    }

    @PutMapping("/journal/id/{myId}")
    public JournalEntity UpdateJournalById(@PathVariable Long myId,@RequestBody JournalEntity myEntry){
        return journalEntries.put(myId,myEntry);
    }

}
