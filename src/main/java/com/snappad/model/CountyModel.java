package com.snappad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snappad.model.StateModel;

@Entity
@Table(name="county")
public class CountyModel {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column
private int id;
@Column
private String Name;
@OneToOne
@JsonIgnore
private StateModel state;
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
public StateModel getState() {
	return state;
}
public void setState(StateModel state) {
	this.state = state;
} 

}
