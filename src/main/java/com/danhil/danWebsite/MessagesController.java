package com.danhil.danWebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/messages")
public class MessagesController {
    @Autowired
    private MessagesService MessagesService;
    @GetMapping
    public ResponseEntity<List<Messages>> allMessages (){
           List<Messages> allMessages = MessagesService.AllMessages();
           if(allMessages.isEmpty()){
               return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
           }
        return new ResponseEntity<List<Messages>>(MessagesService.AllMessages(), HttpStatus.OK);
    }

    //This is how you set /api/v1/messages
    @GetMapping(value = "/{id}")
    public ResponseEntity<Messages> getMessage(@PathVariable("id") String id){
            Messages message = MessagesService.Message(id);
            if(message == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<Messages>(message,HttpStatus.OK);
    }
    //Post api for creating messages
    @PostMapping
    public ResponseEntity<Messages> CreateMessage(@RequestBody Map<String, Object> payload) {
        try {
            String name = (String) payload.getOrDefault("name", "");
            String companyRole = (String) payload.getOrDefault("companyRole", "");
            String companyName = (String) payload.getOrDefault("companyName", "");
            String industry = (String) payload.getOrDefault("industry", "");
            String title = (String) payload.getOrDefault("title", "");
            String text = (String) payload.getOrDefault("text", "");
            String companyUrl = (String) payload.getOrDefault("companyUrl", "");
            List<String> jobType = (List<String>) payload.getOrDefault("jobType", Collections.emptyList());
            boolean read = (boolean) payload.getOrDefault("read", false);

            Messages msg = Messages.builder().name(name).companyRole(companyRole).companyName(companyName).industry(industry).title(title).text(text).companyUrl(companyUrl).jobType(jobType).read(read).build();
            Messages createdMessage = MessagesService.CreateMessage(msg);
            return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Messages> PatchMessage(@PathVariable("id") String id, @RequestBody Map<String, Object> requestPayload){
        try{
            Messages patchMessage = MessagesService.PatchMessage(id, requestPayload);
            if(patchMessage == null){
                return new ResponseEntity<>(NOT_FOUND);
            }
            return new ResponseEntity<>(patchMessage, OK);
        }catch (Exception e){
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }


}
