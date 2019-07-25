package com.maxwell.myCollection.entity;

import java.util.ArrayList;
import java.util.List;

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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
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
	private List<OfferEntity> offers = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
	private List<CommentaryEntity> commentaries = new ArrayList<>();

}
