package com.danhil.danWebsite;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.Key;
import java.util.List;
import java.util.Map;

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

    public messages patchMessage(String id, Map<String, Object> patchPayload){
        ObjectId msgId = new ObjectId(id);
        messages msg = messagesRepository.findById(msgId).orElse(null);
        if (msg == null){
            return null;
        }
        for (Map.Entry<String, Object> entry : patchPayload.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            try {
                Field field = messages.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(msg, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
            return messagesRepository.save(msg);
        }
    }



