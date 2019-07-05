package com.maxwell.myCollection.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "profile")
public class ProfileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "numbertrades", nullable = false)
	private long numbertrades;

	@Column(name = "membersince", nullable = false)
	private String membersince;

	@NotBlank
	@Column(name = "location", nullable = false)
	private String location;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id_fk")
	private UserEntity user;

	public ProfileEntity(@NotBlank String name, @NotBlank String email, long numbertrades, String membersince,
			@NotBlank String location, UserEntity user) {
		super();
		this.name = name;
		this.email = email;
		this.numbertrades = numbertrades;
		this.membersince = membersince;
		this.location = location;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getNumbertrades() {
		return numbertrades;
	}

	public void setNumbertrades(long numbertrades) {
		this.numbertrades = numbertrades;
	}

	public String getMembersince() {
		return membersince;
	}

	public void setMembersince(String membersince) {
		this.membersince = membersince;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}