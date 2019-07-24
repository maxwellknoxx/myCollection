package com.maxwell.myCollection.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.maxwell.myCollection.response.JwtResponse;

@Entity
@Table(name = "profile")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProfileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "username")
	private String username;

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
	@JsonIgnore
	private UserEntity user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
	@JsonIgnore
	private Set<OfferEntity> offers = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
	@JsonIgnore
	private Set<CommentaryEntity> commentaries = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
	@JsonIgnore
	private Set<ReplyEntity> replies = new HashSet<>();

	@Transient
	private JwtResponse jwt;

	public ProfileEntity() {
	}

	public ProfileEntity(@NotBlank String name, String username, @NotBlank String email, long numbertrades,
			String membersince, @NotBlank String location, UserEntity user) {
		super();
		this.name = name;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Set<ReplyEntity> getReplies() {
		return replies;
	}

	public void setReplies(Set<ReplyEntity> replies) {
		this.replies = replies;
	}

	public JwtResponse getJwt() {
		return jwt;
	}

	public void setJwt(JwtResponse jwt) {
		this.jwt = jwt;
	}

	@Override
	public String toString() {
		return "ProfileEntity [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email
				+ ", numbertrades=" + numbertrades + ", membersince=" + membersince + ", location=" + location
				+ ", user=" + user + ", offers=" + offers + ", commentaries=" + commentaries + ", replies=" + replies
				+ ", jwt=" + jwt + "]";
	}

}