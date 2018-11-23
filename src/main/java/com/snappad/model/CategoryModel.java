package com.snappad.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="Category")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class CategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String Name;
	@ManyToOne
	@JoinColumn(name="parent_id")
	private CategoryModel parent;
	@OneToMany(mappedBy="parent")
	@JsonIgnore
	private Set<CategoryModel> subCat;
	public CategoryModel getParent() {
		return parent;
	}
	public void setParent(CategoryModel parent) {
		this.parent = parent;
	}
	public Set<CategoryModel> getSubCat() {
		return subCat;
	}
	public void setSubCat(Set<CategoryModel> subCat) {
		this.subCat = subCat;
	}
	/////////////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

}
