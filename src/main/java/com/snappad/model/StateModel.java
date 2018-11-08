package com.snappad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
@Entity
@Table(name="state")
public class StateModel {
@Id()
@GeneratedValue(strategy=GenerationType.AUTO)
@Column
private int stateid;
@Column
private String StateName;
public int getStateid() {
	return stateid;
}
public void setStateid(int stateid) {
	this.stateid = stateid;
}
public String getStateName() {
	return StateName;
}
public void setStateName(String stateName) {
	StateName = stateName;
}

}
