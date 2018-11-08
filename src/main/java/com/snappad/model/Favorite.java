package com.snappad.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Favorite")
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer FavId;
	@ManyToOne
	@JoinColumn(name = "UserFavId")
	private UserModel Userid;
	@OneToOne(cascade = CascadeType.ALL)
	private AdsModel AdsId;

	public Integer getFavid() {
		return FavId;
	}

	public UserModel getUserid() {
		return Userid;
	}

	public AdsModel getAdsId() {
		return AdsId;
	}

	public void setFavid(Integer favid) {
		FavId = favid;
	}

	public void setUserid(UserModel userid) {
		Userid = userid;
	}
	public void setAdsId(AdsModel adsId) {
		AdsId = adsId;
	}
}
