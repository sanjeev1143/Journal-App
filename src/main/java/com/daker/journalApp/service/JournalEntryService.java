package com.daker.journalApp.service;

import com.daker.journalApp.entity.JournalEntity;
import com.daker.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;


    public void saveEntry(JournalEntity journalEntity){
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepository.findAll();
    }

    public void deleteById(ObjectId id){
         journalEntryRepository.deleteById(id);
    }

    public Optional<JournalEntity> findById(ObjectId id){


        return journalEntryRepository.findById(id);
    }



}
