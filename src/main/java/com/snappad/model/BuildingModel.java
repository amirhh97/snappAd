package com.snappad.model;

import com.snappad.model.AdsModel;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="building")
public class BuildingModel extends AdsModel {
@Column
private Integer lenght;
@Column
private Integer Rooms;
@Column
private Integer RentCost;
@Column
private Integer mortgage;
public Integer getLenght() {
	return lenght;
}
public void setLenght(Integer lenght) {
	this.lenght = lenght;
}
public Integer getRooms() {
	return Rooms;
}
public void setRooms(Integer rooms) {
	Rooms = rooms;
}
public Integer getRentCost() {
	return RentCost;
}
public void setRentCost(Integer rentCost) {
	RentCost = rentCost;
}
public Integer getMortgage() {
	return mortgage;
}
public void setMortgage(Integer mortgage) {
	this.mortgage = mortgage;
}

}
