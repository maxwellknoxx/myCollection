package com.maxwell.myCollection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private CommentaryEntity commentary;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	@JsonIgnore
	private ProfileEntity profile;

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

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

}
