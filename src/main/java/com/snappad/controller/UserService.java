package com.snappad.controller;

import Utility.TokenUtility;
import com.snappad.dao.JpaRepositories.UserRepository;
import com.snappad.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/user")
public class UserService {
	@Autowired
	UserRepository userRepository;
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public String Reg(@RequestParam("firstName") String name,
					  @RequestParam("lastName") String family,
					  @RequestParam("usermail") String mail,
					  @RequestParam("password") String pass,
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
		if (userRepository.exist(number)==null) {
			/*
			 * user.setCityid(c); user.setStateid(s);
			 */
			userRepository.save(user);
			return "ok";
		}
		return "faild";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String Login(@RequestParam("mobileNumber") String Number,
						@RequestParam("userPass") String Pass,
						HttpServletRequest req,
						HttpServletResponse resp) {
		System.out.println("number:" + Number + " pass:" + Pass);
		UserModel user = new UserModel();
		user.setUsermobilenum(Number);
		user.setUserpass(Pass);
		if (userRepository.exists(Example.of(user))) {
			user=userRepository.findOne(Example.of(user)).get();
			String token=TokenUtility.createToken(user);
			user.setToken(token);
			userRepository.save(user);
			return token ;
		}
		return "user not found";
	}
}
