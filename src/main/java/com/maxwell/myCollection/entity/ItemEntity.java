package com.maxwell.myCollection.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "items")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ItemEntity {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id_fk")
	private CategoryEntity category;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "profile_id_fk", nullable = false)
	private ProfileEntity profile;

	@Column(name = "name", nullable = false)
	private String Name;

	@Column(name = "item_condition", nullable = false)
	private String itemCondition;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "photo", nullable = false)
	private String photo;

	@Column(name = "trade", nullable = false)
	private String Trade;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "publish_date", nullable = true)
	private String publishDate;

	@JsonManagedReference(value = "offers")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	@JsonIgnore
	private Set<OfferEntity> offers = new HashSet<>();

	@JsonManagedReference(value = "commentaries")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
	@JsonIgnore
	private Set<CommentaryEntity> commentaries = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTrade() {
		return Trade;
	}

	public void setTrade(String trade) {
		Trade = trade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

	public Set<OfferEntity> getOffers() {
		return offers;
	}

	public void setOffers(Set<OfferEntity> offers) {
		this.offers = offers;
	}

	public Set<CommentaryEntity> getCommentaries() {
		return commentaries;
	}

	public void setCommentaries(Set<CommentaryEntity> commentaries) {
		this.commentaries = commentaries;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "ItemEntity [id=" + id + ", category=" + category + ", profile=" + profile + ", Name=" + Name
				+ ", itemCondition=" + itemCondition + ", description=" + description + ", photo=" + photo + ", Trade="
				+ Trade + ", status=" + status + ", publishDate=" + publishDate + ", offers=" + offers
				+ ", commentaries=" + commentaries + "]";
	}

}
