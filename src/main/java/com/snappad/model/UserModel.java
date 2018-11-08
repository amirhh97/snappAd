package com.snappad.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.snappad.model.AdsModel;
import org.hibernate.engine.internal.Cascade;
import org.hibernate.engine.spi.CascadeStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name="user")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "userid")
public class UserModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer userid;
	@Column
	private String usermail;
	@Column
	private String username;
	@Column
	private String userLastName;
	@Column
	private String userpass;
	@Column
	private String usermobilenum;
	@Column
	@OneToMany(mappedBy="Owner")
	private List<AdsModel> UserAdsId;
	@Column
	@OneToMany(mappedBy="Userid")
	private List<Favorite> UserFavId;
	
	public void setUserid(Integer usarid) {
		this.userid = usarid;
	}
	public Integer getUserid() {
		return userid;
	}
	public String getUsermail() {
		return usermail;
	}
	public void setUsermail(String usarmail) {
		this.usermail = usarmail;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String usarpass) {
		this.userpass = usarpass;
	}
	public String getUsermobilenum() {
		return usermobilenum;
	}
	public void setUsermobilenum(String usarmobilenum) {
		this.usermobilenum = usarmobilenum;
	}
	public List<AdsModel> getAdsId() {
		return UserAdsId;
	}
	public void setAdsId(List<AdsModel> adsid) {
		this.UserAdsId = adsid;
	}
	public List<Favorite> getUserFavId() {
		return UserFavId;
	}
	public void setUserFavId(List<Favorite> userFavId) {
		UserFavId = userFavId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public List<AdsModel> getUserAdsId() {
		return UserAdsId;
	}
	public void setUserAdsId(List<AdsModel> userAdsId) {
		UserAdsId = userAdsId;
	}


}
