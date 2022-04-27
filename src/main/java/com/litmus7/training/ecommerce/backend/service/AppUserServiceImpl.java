package com.litmus7.training.ecommerce.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.litmus7.training.ecommerce.backend.dao.AddressRepository;
import com.litmus7.training.ecommerce.backend.dao.UserCredentialsRepository;
import com.litmus7.training.ecommerce.backend.dao.UserRepository;
import com.litmus7.training.ecommerce.backend.dto.UserDTO;
import com.litmus7.training.ecommerce.backend.entity.Address;
import com.litmus7.training.ecommerce.backend.entity.AppUser;
import com.litmus7.training.ecommerce.backend.entity.UserCredentials;
import com.litmus7.training.ecommerce.backend.exception.UserEmailUniqueException;

@Service
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserCredentialsRepository credRepository;


	@Override
	public List<AppUser> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public AppUser getUser(Long id) {
		return userRepository.getById(id);
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		AppUser user = new AppUser();
		if (credRepository.findByUsername(userDTO.getEmail()) != null) {
			throw new UserEmailUniqueException("email already exists for another user");
		}
		UserCredentials credUser = new UserCredentials();
		user.setActive(true);
		user.setEmail(userDTO.getEmail());
		credUser.setUser_name(userDTO.getEmail());
		user.setMobile_number(userDTO.getMobile_number());
		user.setName(userDTO.getName());
		credUser.setRole("USER");
		credUser.setPassword(new BCryptPasswordEncoder(12).encode(userDTO.getPassword()));
		System.out.println(userDTO);
		user = userRepository.save(user);
		userDTO.setId(user.getId());
		credUser.setUserId(user.getId());
		credRepository.save(credUser);
		Address address = userDTO.getAddress();
		address.setName("default");
		address.setUser(user);
		addressRepository.save(address);
		return userDTO;


	}

	@Override
	public UserDTO getUserById(Long id) {
		AppUser user = userRepository.findById(id).get();
		UserDTO userDTO = new UserDTO();
		userDTO.setAddress(user.getAddresses().stream().filter(add -> add.getUser() == user.getId()).findFirst().get());
		userDTO.setEmail(user.getEmail());
		userDTO.setMobile_number(user.getMobile_number());
		return userDTO;
	}

}
