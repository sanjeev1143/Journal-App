package com.daker.journalApp.service;

import com.daker.journalApp.entity.JournalEntity;
import com.daker.journalApp.entity.User;
import com.daker.journalApp.repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;


    private final UserEntryService userEntryService;


    @Transactional
    public void saveEntry(JournalEntity journalEntity, String username){
        User user = userEntryService.getUserByUsername(username);
        journalEntity.setDate(LocalDateTime.now());
        JournalEntity saved = journalEntryRepository.save(journalEntity);
       user.getJournalEntities().add(saved);
       userEntryService.saveEntry(user);

    }

    public List<JournalEntity> getAll(){
        return journalEntryRepository.findAll();
    }

    public void deleteById(ObjectId id,String username){
        User user = userEntryService.getUserByUsername(username);
        user.getJournalEntities().removeIf(x->x.getId().equals(id));
        userEntryService.saveEntry(user);
         journalEntryRepository.deleteById(id);
    }

    public void updateById(ObjectId id,String username,JournalEntity new_entry){

        JournalEntity old = journalEntryRepository.findById(id).orElse(null);

        if(old!=null){
            old.setTitle(new_entry.getTitle()!=null && !new_entry.getTitle().equals("")?new_entry.getTitle():old.getTitle());
            old.setContent(new_entry.getContent()!=null && !new_entry.getContent().equals("")?new_entry.getContent():old.getContent());
            journalEntryRepository.save(old);

        }



    }



    public Optional<JournalEntity> findById(ObjectId id){


        return journalEntryRepository.findById(id);
    }



}
