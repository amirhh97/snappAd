package com.snappad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snappad.model.CityModel;

@Entity
@Table(name="district")
public class DistrictModel {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column
private int id;
@Column
private String Name;
@ManyToOne
@JoinColumn(name="Cityid")
CityModel City;
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
@JsonIgnore
public CityModel getCity() {
	return City;
}
public void setCity(CityModel city) {
	City = city;
}
}
