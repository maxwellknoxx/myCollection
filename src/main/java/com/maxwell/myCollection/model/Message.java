package com.maxwell.myCollection.model;

public class Message {

	private Long id;
	private Long idSender;
	private Long idRecipient;
	private String messageText;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdSender() {
		return idSender;
	}

	public void setIdSender(Long idSender) {
		this.idSender = idSender;
	}

	public Long getIdRecipient() {
		return idRecipient;
	}

	public void setIdRecipient(Long idRecipient) {
		this.idRecipient = idRecipient;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", idSender=" + idSender + ", idRecipient=" + idRecipient + ", messageText="
				+ messageText + "]";
	}

}
