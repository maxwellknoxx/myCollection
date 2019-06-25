package com.maxwell.myCollection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items_comments")
public class ItemCommentsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "id_item", nullable = false)
	private Long idItem;
	
	@Column(name = "id_comment", nullable = false)
	private Long idComment;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
}
