package com.snappad.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="city")
@JsonIgnoreProperties({"county","districts"})
@JsonPropertyOrder({"cityId","cityName","state"})
public class CityModel {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column
private int CityId;
@Column
private String cityname;
@OneToOne
private StateModel State;
@OneToOne
@JsonIgnore()
private CountyModel County;
@OneToMany(mappedBy="City")
@Column
@JsonIgnore()
private List<DistrictModel> Districts;
public StateModel getState() {
	return State;
}
public void setState(StateModel state) {
	State = state;
}
public CountyModel getCounty() {
	return County;
}
public void setCounty(CountyModel county) {
	County = county;
}
public int getCityId() {
	return CityId;
}
public void setCityId(int cityId) {
	CityId = cityId;
}
public String getCityName() {
	return cityname;
}
public void setCityName(String cityName) {
	cityname = cityName;
}
public List<DistrictModel> getDistricts() {
	return Districts;
}
public void setDistricts(List<DistrictModel> districts) {
	Districts = districts;
}

}
