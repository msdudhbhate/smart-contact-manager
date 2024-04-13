package com.smart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//using common data to call all attribute in on single method.
	@ModelAttribute
	public void addCommonData(Model model,Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME"+userName);
		//get user deatils by using username (email)
		User user = userRepository.getUserByUserName(userName);
		System.out.println("USER"+user);
		model.addAttribute("user"+user);	
		
	}
	//home dashboard
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		model.addAttribute("title","User dashboard");
		model.addAttribute("contact",new Contact());
		return "normal/user_dashboard";
	}
	//open add form handler-----
	@GetMapping("/add-contact")
	public String openAddContactform( Model model) {
		model.addAttribute("title","Add Contact");
		return "normal/add_contact_form";
	}
	

}
