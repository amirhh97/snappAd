package com.snappad.model;

import com.snappad.model.AdsModel;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="vehicle")
public class VehicleModel extends AdsModel {
@Column
private Integer ProduceYear;
@Column
private String Brand;
@Column
private Integer Kilometer;
@Column
private String Color;
public Integer getProduceYear() {
	return ProduceYear;
}
public void setProduceYear(Integer produceYear) {
	ProduceYear = produceYear;
}
public String getBrand() {
	return Brand;
}
public void setBrand(String brand) {
	Brand = brand;
}
public Integer getKilometer() {
	return Kilometer;
}
public void setKilometer(Integer kilometer) {
	Kilometer = kilometer;
}
public String getColor() {
	return Color;
}
public void setColor(String color) {
	Color = color;
}

}
