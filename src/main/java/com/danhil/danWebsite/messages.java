package com.danhil.danWebsite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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
    @DocumentReference
    private List<Replies> replies;

    public messages(String name, String companyRole, String companyName, String industry, String title, String text, String companyUrl, List<String> jobType, Boolean read, List<Replies> replies){
        this.name = name;
        this.companyRole = companyRole;
        this.companyName = companyName;
        this.industry = industry;
        this.title = title;
        this.text = text;
        this.companyUrl = companyUrl;
        this.jobType = jobType;
        this.read = read;
        this.replies = replies;
    }

}
