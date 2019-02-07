package com.snappad.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
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
	@OneToMany(mappedBy="Owner")
	private List<AdsModel> UserAds;
	@OneToMany(mappedBy="Userid")
	private List<Favorite> UserFavId;
	@Column
	private String Token;

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
		return UserAds;
	}
	public void setAdsId(List<AdsModel> adsid) {
		this.UserAds = adsid;
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
		return UserAds;
	}
	public void setUserAdsId(List<AdsModel> userAdsId) {
		UserAds = userAdsId;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}
