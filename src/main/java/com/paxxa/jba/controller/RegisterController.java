package com.paxxa.jba.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paxxa.jba.entity.User;
import com.paxxa.jba.service.UserService;



@Controller
public class RegisterController {
	
	
	@Autowired
	private UserService userService;
	
		// Object binding from HTML (user-register.jsp) using ModelAttribute
		// CommandName describe form and it is used in ModelAttrubute to link to the form
		// path in form must cover attributes from user entity (it corresponds to the attributes)
		// pressing Save button from the form we will perform post method
	@ModelAttribute("user")
	private User constructUser() {
		return new User();
		}
	
	@RequestMapping("/register")
	public String showRegister(){
		return "user-register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	//(@ModelAttribute("user") User user) - getting user object from form registration
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result){
		if(result.hasErrors()){
			return "user-register";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	
	@RequestMapping("/register/available")
	@ResponseBody
	public String available(@RequestParam String username){
		Boolean available = userService.findOne(username) == null;
		return available.toString();
		
	}

	
	
}
