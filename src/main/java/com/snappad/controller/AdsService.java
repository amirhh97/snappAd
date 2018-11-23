package com.snappad.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.snappad.dao.AdsDao;
import com.snappad.dao.CategoryDao;
import com.snappad.dao.JpaRepositories.AdsRepository;
import com.snappad.dao.JpaRepositories.UserRepository;
import com.snappad.dao.LocationDao;
import com.snappad.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/ad")
public class AdsService {

	@Autowired
	AdsRepository adsRepository;

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody()
	public String RegAd(@RequestParam("Describe") String Describe, @RequestParam("State") String State,
			@RequestParam("City") String City, @RequestParam("district") String District,
			@RequestParam("price") Integer Price, @RequestParam("cat") String category,
			@RequestParam("manufacturer") String Manufactor, @RequestParam("brand") String brand,
			@RequestParam("poroductYear") Integer year, @RequestParam("title") String Title,
			@RequestParam("img") String img, @RequestParam("kilometer") Integer Km, @RequestParam("color") String Color,
			@RequestParam("Rooms") Integer rooms, @RequestParam("mortgage") Integer ejare,
			@RequestParam("rent") Integer Rent, @RequestParam("lenght") Integer Lenght, HttpServletRequest req) {
		System.out.println(State + " " + Rent + " " + District + " " + category + " " + Title + " " + Describe);
		LocationDao ldb = new LocationDao();
		StateModel s = ldb.getStateById(Integer.valueOf(State));
		CityModel c = ldb.getCityById(Integer.valueOf(City));
		DistrictModel district = ldb.getDistrictByName(District, c);
		CategoryModel cat = new CategoryDao().getCatById(Integer.valueOf(category));
		UserModel owner = new UserModel();
		// owner.setUserid((int) req.getSession().getAttribute("userid"));
		if (Integer.valueOf(category) >= 8 && Integer.valueOf(category) <= 14) {
			System.out.println("car");
			VehicleModel ads = new VehicleModel();
			ads.setAdsCity(c);
			ads.setAdsState(s);
			ads.setAdsDescribe(Describe);
			ads.setCat(cat);
			ads.setAdsTitle(Title);
			ads.setAdsDate(new Date());
			ads.setKilometer(Km);
			ads.setPrice(Price);
			ads.setColor(Color);
			ads.setProduceYear(year);
			ads.setBrand(brand);
			// ads.setOwner(owner);
			new AdsDao().RegAds(ads);
			return "ok";

		} else if (cat.getId() >= 15 && cat.getId() <= 20) {
			System.out.println("bi");
			BuildingModel ads = new BuildingModel();
			ads.setAdsCity(c);
			ads.setAdsState(s);
			ads.setAdsDescribe(Describe);
			ads.setCat(cat);
			ads.setAdsTitle(Title);
			ads.setAdsDate(new Date());
			ads.setLenght(Lenght);
			ads.setRooms(rooms);
			ads.setMortgage(ejare);
			ads.setRentCost(Rent);
			new AdsDao().RegAds(ads);
			return "ok";
		}
		AdsModel ads = new AdsModel();
		ads.setAdsCity(c);
		ads.setAdsState(s);
		ads.setAdsDescribe(Describe);
		ads.setCat(cat);
		ads.setAdsTitle(Title);
		ads.setPrice(Price);
		ads.setAdsDate(new Date());
		return "ok";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<AdsModel> Home() {

		List<AdsModel> ads =adsRepository.findAll();
		for (AdsModel x : ads)
			x.setDate(x.computeDiff());
		return ads;

	}

}
