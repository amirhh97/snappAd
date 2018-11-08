package com.snappad.controller;

import java.lang.reflect.Method;
import java.util.List;

import com.snappad.dao.AdsDao;
import com.snappad.dao.CategoryDao;
import com.snappad.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import snappadd.model.CategoryModel;

@Controller
@RequestMapping("/")
public class RootController {
	@RequestMapping("/")
	public List index()
	{	
		return new AdsDao().getAllAds();
	}
@RequestMapping("/reg")
public ModelAndView RegisterUser(@ModelAttribute("UserModel") UserModel model)
{	
	return new ModelAndView("reg");
}
@RequestMapping("/login")
public ModelAndView LoginUser(@ModelAttribute("model") UserModel model)
{
	return new ModelAndView("Login").addObject("url","user/");
}
@RequestMapping("/cat")
@ResponseBody
List<CategoryModel> getAllCategory()
{
	CategoryDao db=new CategoryDao();
	return db.getAllCat();
}
}

