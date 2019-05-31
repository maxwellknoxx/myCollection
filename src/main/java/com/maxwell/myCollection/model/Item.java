package com.maxwell.myCollection.model;

public class Item {

	private Long id;
	private Long idOwner;
	private String Category;
	private String Name;
	private String condition;
	private String description;
	private String photo;
	private String Trade;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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
		return "Item [id=" + id + ", idOwner=" + idOwner + ", Category=" + Category + ", Name=" + Name + ", condition="
				+ condition + ", description=" + description + ", photo=" + photo + ", Trade=" + Trade + ", status="
				+ status + "]";
	}

}
