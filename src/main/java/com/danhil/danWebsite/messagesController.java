package com.danhil.danWebsite;

import org.apache.logging.log4j.message.Message;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;


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
    public ResponseEntity<messages> getMessage(@PathVariable("id") String id){
            messages message = messagesService.message(id);
            if(message == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<messages>(message,HttpStatus.OK);
    }
    //Post api for creating messages
    @PostMapping
    public ResponseEntity<messages> createMessage(@RequestBody Map<String, Object> payload) {
        try {
            String name = (String) payload.getOrDefault("name", "");
            String companyRole = (String) payload.getOrDefault("companyRole", "");
            String companyName = (String) payload.getOrDefault("companyName", "");
            String industry = (String) payload.getOrDefault("industry", "");
            String title = (String) payload.getOrDefault("title", "");
            String text = (String) payload.getOrDefault("text", "");
            String companyUrl = (String) payload.getOrDefault("companyUrl", "");
            List<String> jobType = (List<String>) payload.getOrDefault("jobType", Collections.emptyList());
            Boolean read = (Boolean) payload.getOrDefault("read", false);

            messages msg = new messages(
                    name, companyRole, companyName, industry, title, text, companyUrl, jobType, read, null
            );
            messages createdMessage = messagesService.createMessage(msg);
            return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
