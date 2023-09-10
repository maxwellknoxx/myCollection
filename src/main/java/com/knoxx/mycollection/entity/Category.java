package com.knoxx.mycollection.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@Document("categories")
public class Category {

    @Id
    private String categoryId;
    private String name;
    private String description;

}
