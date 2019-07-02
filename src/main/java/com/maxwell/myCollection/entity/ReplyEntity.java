package com.maxwell.myCollection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "replies")
public class ReplyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "reply")
	private String reply;

	@ManyToOne
	@JoinColumn(name = "commentary_id")
	private CommentaryEntity commentary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public CommentaryEntity getCommentary() {
		return commentary;
	}

	public void setCommentary(CommentaryEntity commentary) {
		this.commentary = commentary;
	}

}
