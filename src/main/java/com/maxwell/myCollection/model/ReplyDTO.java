package com.maxwell.myCollection.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ReplyDTO {

	private Long id;
	private String reply;
	private Long commentaryId;
	private Long profileId;
	private String profileName;

}
