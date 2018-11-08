package com.snappad.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.snappad.dao.UserDao;
import com.snappad.model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Utility.TokenUtility;


@Controller
@RequestMapping(value = "/user")
public class UserService {
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public String Reg(@RequestParam("firstName") String name, @RequestParam("lastName") String family,
			@RequestParam("usermail") String mail, @RequestParam("password") String pass,
			@RequestParam("mobileNumber") String number/*
														 * , @RequestParam(
														 * "state") String
														 * statename
														 */) {
		UserModel user = new UserModel();
		user.setUsername(name);
		user.setUsermobilenum(number);
		user.setUserLastName(family);
		user.setUsermail(mail);
		user.setUserpass(pass);
		/*
		 * StateModel s = new StateModel(); s.setStateName(statename); CityModel
		 * c = new CityModel(); c.setCityName(cityname);
		 */
		UserDao db = new UserDao();
		if (!db.UserExist(user.getUsermobilenum())) {
			/*
			 * user.setCityid(c); user.setStateid(s);
			 */
			db.RegUser(user);
			return "ok";
		}
		return "faild";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String Login(@RequestParam("mobileNumber") String Number, @RequestParam("userPass") String Pass,
			HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("number:" + Number + " pass:" + Pass);
		UserModel user = new UserModel();
		user.setUsermobilenum(Number);
		user.setUserpass(Pass);
		UserDao db = new UserDao();
		int userid = db.login(user.getUsermobilenum(), user.getUserpass());
		if (userid > 0) {
			return TokenUtility.createToken(user);
		}
		return "user not found";
	}
}
