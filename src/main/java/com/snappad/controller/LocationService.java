package com.snappad.controller;

import java.util.List;

import com.snappad.dao.LocationDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/location")
public class LocationService {
	@RequestMapping("/city")
	@ResponseBody
	public List getCity()
	{
		return new LocationDao().getAllCities();
	}
	@RequestMapping("/state")
	@ResponseBody
	public List getState()
	{
		return new LocationDao().getAllState();
	}
	
}
