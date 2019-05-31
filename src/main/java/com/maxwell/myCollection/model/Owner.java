package com.maxwell.myCollection.model;

public class Owner {

	private Long id;
	private String user;
	private String password;
	private String question;
	private String answer;
	private Long numberTrades;
	private String email;
	private String memberSince;
	private String location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getNumberTrades() {
		return numberTrades;
	}

	public void setNumberTrades(Long numberTrades) {
		this.numberTrades = numberTrades;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(String memberSince) {
		this.memberSince = memberSince;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", user=" + user + ", password=" + password + ", question=" + question + ", answer="
				+ answer + ", numberTrades=" + numberTrades + ", email=" + email + ", memberSince=" + memberSince
				+ ", location=" + location + "]";
	}

}
