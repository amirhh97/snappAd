package com.snappad.controller;

import com.snappad.dao.JpaRepositories.CityRepository;
import com.snappad.dao.JpaRepositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationService {

	CityRepository cityRepository;
	StateRepository stateRepository;

	@Autowired
	public LocationService(CityRepository cityRepository, StateRepository stateRepository) {
		this.cityRepository = cityRepository;
		this.stateRepository = stateRepository;
	}

	@RequestMapping("/city")
	@ResponseBody
	public List getCity()
	{
		return cityRepository.findAll();
	}
	@RequestMapping("/state")
	@ResponseBody
	public List getState()
	{
		return stateRepository.findAll();
	}
	
}
