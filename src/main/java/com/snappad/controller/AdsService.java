package com.snappad.controller;

import com.snappad.dao.JpaRepositories.*;
import com.snappad.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/ad")
public class AdsService {


	private AdsRepository adsRepository;
	private CategoryRepository categoryRepository;
	private CityRepository cityRepository;
	private StateRepository stateRepository;
	private DistrictRepository districtRepository;
	@Autowired
	public AdsService(AdsRepository adsRepository, CategoryRepository categoryRepository, CityRepository cityRepository, StateRepository stateRepository, DistrictRepository districtRepository) {
		this.adsRepository = adsRepository;
		this.categoryRepository = categoryRepository;
		this.cityRepository = cityRepository;
		this.stateRepository = stateRepository;
		this.districtRepository = districtRepository;
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody()
	public String RegAd(@RequestParam("Describe") String Describe, @RequestParam("State") String State,
						@RequestParam("City") String City, @RequestParam("district") String District,
						@RequestParam(value = "price", required = false) Integer Price, @RequestParam("cat") String category,
						@RequestParam(value = "manufacturer", required = false) String Manufactor,
						@RequestParam(value = "brand", required = false) String brand,
						@RequestParam(value = "poroductYear", required = false) Integer year,
						@RequestParam("title") String Title,
						@RequestParam("img") String img,
						@RequestParam(value = "kilometer", required = false) Integer Km,
						@RequestParam(value = "color", required = false) String Color,
						@RequestParam(value = "Rooms", required = false) Integer rooms,
						@RequestParam(value = "mortgage", required = false) Integer ejare,
						@RequestParam(value = "rent", required = false) Integer Rent,
						@RequestParam(value = "lenght", required = false) Integer Lenght, HttpServletRequest req) throws Exception {
		System.out.println(State + " " + Rent + " " + District + " " + category + " " + Title + " " + Describe);
		StateModel s = stateRepository.getOne(Integer.valueOf(State));
		CityModel c = cityRepository.getOne(Integer.valueOf(City));
		//todo ino search kon bbin methode find one chejori kar mikone badesh khate paino dorost kon
		DistrictModel district = districtRepository.findByCity(c.getCityId(), District);
		if (district == null) {
			district = new DistrictModel();
			district.setName(District);
			district.setCity(c);
			districtRepository.save(district);/*getDistrictByName(District, c)*/
		}

		CategoryModel cat = categoryRepository.getOne(Integer.valueOf(category));
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
			adsRepository.save(ads);
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
			adsRepository.save(ads);
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
		adsRepository.save(ads);
		return "ok";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<AdsModel> getAllAds() {

		List<AdsModel> ads =adsRepository.findAll();
		for (AdsModel x : ads)
			x.setDate(x.computeDiff());
		return ads;

	}

	@PostMapping(value = "/img", headers = "content-type=multipart/form-data")
	String adsImage(@RequestParam("file") MultipartFile clientFile)  {
		File dir=new File("C:\\Users\\Amirhosein\\Desktop\\temp");
		if(!dir.exists()){
			dir.mkdir();
		}
		File file=new File(dir.getAbsolutePath()+"\\"+clientFile.getOriginalFilename());
        FileOutputStream os= null;
        try {
            os = new FileOutputStream(file);
			os.write(clientFile.getBytes());
			os.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
		return "ok";
	}


}
