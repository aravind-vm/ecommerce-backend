package com.litmus7.training.ecommerce.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<AppUser> getUsers() {
		return userService.getAllUsers();

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public UserDTO getUsersById(@PathVariable Long id) {
		return userService.getUserById(id);

	}

	@PostMapping("/signup")
	public UserDTO createUsers(@RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}

}
