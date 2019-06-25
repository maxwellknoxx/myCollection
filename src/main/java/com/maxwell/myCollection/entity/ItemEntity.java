package com.maxwell.myCollection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "id_owner", nullable = false)
	private Long idOwner;

	@Column(name = "category", nullable = false)
	private String Category;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(Long idOwner) {
		this.idOwner = idOwner;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
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

	@Override
	public String toString() {
		return "ItemEntity [id=" + id + ", idOwner=" + idOwner + ", Category=" + Category + ", Name=" + Name
				+ ", itemCondition=" + itemCondition + ", description=" + description + ", photo=" + photo + ", Trade=" + Trade
				+ ", status=" + status + "]";
	}

}
