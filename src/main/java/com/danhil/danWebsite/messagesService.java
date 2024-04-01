package com.danhil.danWebsite;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messagesService {
    //intantiates the class foor us
    @Autowired
    private messagesRepository messagesRepository;
    public List<messages> allMessages(){
        return messagesRepository.findAll();
    }

    public messages message(String id){
        ObjectId mongoId = new ObjectId(id);
        return messagesRepository.findById(mongoId).orElse(null);
    }

    public messages createMessage( messages msg) {
        return messagesRepository.save(msg);
    }

}
