package com.maxwell.myCollection.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }) })
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3523669452536912309L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@NaturalId
	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@NaturalId
	@Email
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "question", nullable = false)
	private String question;

	@Column(name = "answer", nullable = false)
	private String answer;

	@Column(name = "location", nullable = false)
	private String location;

	@Column(name = "member_since", nullable = false)
	private String memberSince;

	@Column(name = "number_trades", nullable = false)
	private Long numberTrades;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<>();

	public UserEntity(String name, String username, String password, String email, String question, String answer,
			String location, String memberSince, Long numberTrades, Set<RoleEntity> roles) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.question = question;
		this.answer = answer;
		this.location = location;
		this.memberSince = memberSince;
		this.numberTrades = numberTrades;
		this.roles = roles;

	}

}
