package com.litmus7.training.ecommerce.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litmus7.training.ecommerce.backend.dto.UserDTO;
import com.litmus7.training.ecommerce.backend.entity.AppUser;
import com.litmus7.training.ecommerce.backend.service.AppUserService;

@CrossOrigin(origins = { "http://localhost:3000/", "http://localhost:3001/" })
@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private AppUserService userService;

	@GetMapping("")
	public List<AppUser> getUsers() {
		return userService.getAllUsers();

	}

	@PostMapping("")
	public UserDTO createUsers(@RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}

}
