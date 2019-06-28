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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long user_id;

	@OneToOne
	@JoinTable(name = "item_category", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private CategoryEntity category;

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

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "item_offers", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private Set<OfferEntity> itemOffers = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "items_commentaries", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "commentary_id"))
	private Set<CommentaryEntity> itemCommentaries = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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

	public Set<OfferEntity> getItemOffers() {
		return itemOffers;
	}

	public void setItemOffers(Set<OfferEntity> itemOffers) {
		this.itemOffers = itemOffers;
	}

	public Set<CommentaryEntity> getItemCommentaries() {
		return itemCommentaries;
	}

	public void setItemCommentaries(Set<CommentaryEntity> itemCommentaries) {
		this.itemCommentaries = itemCommentaries;
	}

}
