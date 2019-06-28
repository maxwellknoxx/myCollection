package com.maxwell.myCollection.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "commentaries")
public class CommentaryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "commentary")
	private String commentary;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "commentary_reply", joinColumns = @JoinColumn(name = "commentary_id"), inverseJoinColumns = @JoinColumn(name = "reply_id"))
	private Set<ReplyEntity> replies = new HashSet<>();
	
	
}
