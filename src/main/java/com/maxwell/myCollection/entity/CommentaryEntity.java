package com.maxwell.myCollection.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "commentaries")
public class CommentaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "commentary")
	private String commentary;

	@ManyToOne
	@JoinColumn(name = "item_id")
	@JsonBackReference("item_commentary")
	private ItemEntity item;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profile_id")
	private ProfileEntity profile;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commentary")
	private List<ReplyEntity> replies = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public List<ReplyEntity> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyEntity> replies) {
		this.replies = replies;
	}

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "CommentaryEntity [id=" + id + ", commentary=" + commentary + ", item=" + item + ", profile=" + profile
				+ ", replies=" + replies + "]";
	}

}
