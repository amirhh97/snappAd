package com.snappad.controller;

import com.snappad.dao.JpaRepositories.AdsRepository;
import com.snappad.dao.JpaRepositories.CategoryRepository;
import com.snappad.model.AdsModel;
import com.snappad.model.CategoryModel;
import com.snappad.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/")
public class RootController {

	AdsRepository adsRepository;
	CategoryRepository catRepository;

	@Autowired
	public RootController(AdsRepository adsRepository, CategoryRepository catRepository) {
		this.adsRepository = adsRepository;
		this.catRepository = catRepository;
	}

	@RequestMapping("/")
	public String index() {
		return new String("menu");
		//return adsRepository.findAll();
	}

	/*@RequestMapping("/reg")

	public ModelAndView RegisterUser(@ModelAttribute("UserModel") UserModel model)
{	
	return new ModelAndView("reg");
}

	@RequestMapping("/login")
	public ModelAndView LoginUser(@ModelAttribute("model") UserModel model)
{
	return new ModelAndView("Login").addObject("url","user/");
}*/
	@RequestMapping("/cat")
	@ResponseBody
	List<CategoryModel> getAllCategory() {
		return catRepository.findAll();
}
}

