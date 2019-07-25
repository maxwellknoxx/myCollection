package com.maxwell.myCollection.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Commentary {

	private Long id;
	private String commentary;
	private Long itemId;
	private String profileName;
	private Long profileId;
	private List<Reply> replies;

}
