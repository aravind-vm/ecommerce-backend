package com.litmus7.training.ecommerce.backend.service;

import java.util.List;

import com.litmus7.training.ecommerce.backend.dto.UserDTO;
import com.litmus7.training.ecommerce.backend.entity.AppUser;

public interface AppUserService {

	public List<AppUser> getAllUsers();

	public UserDTO createUser(UserDTO userDTO);

	AppUser getUser(Long id);

	public UserDTO getUserById(Long id);

}
