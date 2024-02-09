package com.springsecurity.springsecurity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.springsecurity.entity.UserInfo;
import com.springsecurity.springsecurity.service.UserService;

@RestController
@RequestMapping("secure")
public class HelloController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/newuser")
	public String createUser(@RequestBody UserInfo userInfo) {
		return userService.addUser(userInfo);
	}
	
    @GetMapping("/home")
    public String home() {
        
        
        return "Welcome,!";
    }

    
    @GetMapping("/admin/hello")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminHello( ) {
        return "Hello Admin!" ;
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userHello() {
        return "Hello User!";
    }
}
