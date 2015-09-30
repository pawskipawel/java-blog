package com.paxxa.jba.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paxxa.jba.entity.Blog;
import com.paxxa.jba.entity.User;
import com.paxxa.jba.service.BlogService;
import com.paxxa.jba.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	
	
	// Object binding from HTML (user-register.jsp) using ModelAttribute
	// CommandName describe form and it is used in ModelAttrubute to link to the form
	// path in form must cover attributes from user entity (it corresponds to the attributes)
	// pressing Save button from the form we will perform post method
	@ModelAttribute("user")
	private User constructUser(){
		return new User();
	}
	
	@ModelAttribute("blog")
	private Blog constructBlog(){
		return new Blog();
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
		return "redirect:/register.html?succes=true";
	}
	
	@RequestMapping("/account")
	public String account(Model model, Principal principal){
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "user-detail";
	}
	
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public String doAddBlog(@ModelAttribute("blog") Blog blog, Principal principal){
		String name = principal.getName();
		blogService.save(blog, name);
		return "redirect:/account.html";
	}
}
