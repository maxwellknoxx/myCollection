package com.maxwell.myCollection.model;

public class ItemComments {

	private Long id;
	private Long idItem;
	private Long idComment;
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ItemComments [id=" + id + ", idItem=" + idItem + ", idComment=" + idComment + ", comment=" + comment
				+ "]";
	}

}
