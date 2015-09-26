package com.paxxa.jba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paxxa.jba.entity.User;
import com.paxxa.jba.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	// Object binding from HTML (user-register.jsp) using ModelAttribute
	// CommandName describe form and it is used in ModelAttrubute to link to the form
	// path in form must cover attributes from user entity (it corresponds to the attributes)
	// pressing Save button from the form we will perform post method
	@ModelAttribute("user")
	private User construct(){
		return new User();
	}

	@RequestMapping("/users.html")
	public String users(Model model){
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable int id){
		model.addAttribute("user", userService.findOneWithBlogs(id));
		return "user-detail";
	}

	@RequestMapping("/register")
	public String showRegister(){
		return "user-register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	//(@ModelAttribute("user") User user) - getting user object from form registration
	public String doRegister(@ModelAttribute("user") User user){
		userService.save(user);
		return "user-register";
	}
}
