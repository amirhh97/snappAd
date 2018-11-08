package com.snappad.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "general")
@Table(name = "ads")
public class AdsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer AdsId;
    @Column
    private String AdsTitle;
    @Column
    private String AdsDescribe;
    @ManyToOne
    @JoinColumn(name = "UserAdsId")
    private UserModel Owner;
    @Column
    private Date AdsDate;
    @OneToOne(cascade = CascadeType.ALL)
    private StateModel AdsState;
    @OneToOne(cascade = CascadeType.ALL)
    private CityModel AdsCity;
    @OneToOne(cascade = CascadeType.ALL)
    private snappadd.model.CategoryModel Cat;
    @Column
    private Integer Price;
    @Column
    private String Image_url;
    private String Date;

    //////////////////////
    public snappadd.model.CategoryModel getCat() {
        return Cat;
    }

    public void setCat(snappadd.model.CategoryModel cat) {
        Cat = cat;
    }

    public Integer getAdsId() {
        return AdsId;
    }

    public void setAdsId(Integer adsId) {
        AdsId = adsId;
    }

    public String getAdsTitle() {
        return AdsTitle;
    }

    public void setAdsTitle(String adsTitle) {
        AdsTitle = adsTitle;
    }

    public String getAdsDescribe() {
        return AdsDescribe;
    }

    public void setAdsDescribe(String adsDescribe) {
        AdsDescribe = adsDescribe;
    }

    public UserModel getOwner() {
        return Owner;
    }

    public void setOwner(UserModel owner) {
        Owner = owner;
    }

    @JsonIgnore
    public Date getAdsDate() {
        return AdsDate;
    }

    public void setAdsDate(Date adsDate) {
        AdsDate = adsDate;
    }

    @JsonIgnore
    public StateModel getAdsState() {
        return AdsState;
    }

    public void setAdsState(StateModel adsState) {
        AdsState = adsState;
    }

    public CityModel getAdsCity() {
        return AdsCity;
    }

    public void setAdsCity(CityModel adsCity) {
        AdsCity = adsCity;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public String getImage_url() {
        return Image_url;
    }

    public void setImage_url(String image_url) {
        Image_url = image_url;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(Map<TimeUnit, Long> date) {
        Long Hour = date.get(TimeUnit.HOURS);
        Long Day = date.get(TimeUnit.DAYS);
        if (Day == 0) {
            if (Hour == 0)
                this.Date = "لحظاتی پیش";
            if (Hour != 0)
                this.Date = "ساعاتی پیش";
            return;
        }
        if (Day < 31) {
            this.Date = Day + "روز پیش";
            return;
        }
        if (Day < 366) {
            int d = (int) (Day / 30);
            this.Date = d + "ماه پیش";
            return;
        }
        if (Day > 366) {
            int y = (int) (Day / 365);
            this.Date = y + "ماه پیش";
            return;
        }
    }

    public Map<TimeUnit, Long> computeDiff() {
        long diffInMillies = new Date().getTime() - this.getAdsDate().getTime();
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);
        Map<TimeUnit, Long> result = new LinkedHashMap<TimeUnit, Long>();
        long milliesRest = diffInMillies;
        for (TimeUnit unit : units) {
            long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            milliesRest = milliesRest - diffInMilliesForUnit;
            result.put(unit, diff);
        }
        return result;
    }

}
