package com.knoxx.mycollection.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class UserDto {

    private String userId;
    private String name;
    private String username;
    private String email;
    private String location;
    private String memberSince;
    private Long numberTrades;

}
