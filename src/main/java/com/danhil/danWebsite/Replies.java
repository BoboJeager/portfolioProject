package com.danhil.danWebsite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "replies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Replies {
    @Id
    private ObjectId Id;
    private ObjectId parentId;
    private String title;
    private String text;
    private LocalDateTime dateTime;
}
