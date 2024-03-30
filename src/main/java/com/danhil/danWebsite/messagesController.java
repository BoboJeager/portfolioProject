package com.danhil.danWebsite;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/messages")
public class messagesController {
    @Autowired
    private messagesService messagesService;
    @GetMapping
    public ResponseEntity<List<messages>> allMessages (){
           List<messages> allMessages = messagesService.allMessages();
           if(allMessages.isEmpty()){
               return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
           }
        return new ResponseEntity<List<messages>>(messagesService.allMessages(), HttpStatus.OK);
    }

    //This is how you set /api/v1/messages
    @GetMapping(value = "/{id}")
    public ResponseEntity<messages> Message(@PathVariable("id") String id){
            messages message = messagesService.message(id);
            System.out.println(message);
            if(message == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<messages>(message,HttpStatus.OK);
    }
}
