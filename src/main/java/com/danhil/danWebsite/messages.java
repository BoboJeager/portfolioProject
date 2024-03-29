package com.danhil.danWebsite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "messages")
//lets lombok take care of getters and setters
@Data
@AllArgsConstructor
@NoArgsConstructor
public class messages {
    @Id
    private ObjectId id;
    private String name;
    private String companyRole;
    private String companyName;
    private String industry;
    private String title;
    private String text;
    private String companyUrl;
    private List<String> jobType;
    private Boolean read;
}