package com.knoxx.mycollection.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("user")
public class User implements Serializable {

    private String userId;
    private String name;
    private String username;
    private String email;
    private String location;
    private String memberSince;
    private Long numberTrades;

    public User(String name, String username, String email, String location, String memberSince, Long numberTrades) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.location = location;
        this.memberSince = memberSince;
        this.numberTrades = numberTrades;
    }

}
