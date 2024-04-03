package com.danhil.danWebsite;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class MessagesService {
    //instantiates the class for us
    @Autowired
    private MessagesRepository MessagesRepository;
    public List<messages> AllMessages(){
        return MessagesRepository.findAll();
    }

    public messages Message(String id){
        ObjectId mongoId = new ObjectId(id);
        return MessagesRepository.findById(mongoId).orElse(null);
    }

    public messages CreateMessage( messages msg) {
        return MessagesRepository.save(msg);
    }

    public messages PatchMessage(String id, Map<String, Object> patchPayload){
        ObjectId msgId = new ObjectId(id);
        messages msg = MessagesRepository.findById(msgId).orElse(null);
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
            return MessagesRepository.save(msg);
        }
    }



