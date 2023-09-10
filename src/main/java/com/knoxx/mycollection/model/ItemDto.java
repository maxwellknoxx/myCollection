package com.knoxx.mycollection.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ItemDto {

    private String itemId;
    private String categoryName;
    private String categoryId;
    private String itemName;
    private String userName;
    private String userId;
    private String itemCondition;
    private String description;
    private String photo;
    private String trade;
    private String status;
    private String location;
    private String publishDate;
    //private List<CommentaryDTO> commentaries;

}
