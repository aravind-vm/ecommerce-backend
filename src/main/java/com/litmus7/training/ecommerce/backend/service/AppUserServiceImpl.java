package com.litmus7.training.ecommerce.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litmus7.training.ecommerce.backend.dao.AddressRepository;
import com.litmus7.training.ecommerce.backend.dao.UserRepository;
import com.litmus7.training.ecommerce.backend.dto.UserDTO;
import com.litmus7.training.ecommerce.backend.entity.Address;
import com.litmus7.training.ecommerce.backend.entity.AppUser;
import com.litmus7.training.ecommerce.backend.exception.UserEmailUniqueException;

@Service
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;

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
		if (userRepository.findByEmail(userDTO.getEmail()) != null) {
			throw new UserEmailUniqueException("email already exists for another user");
		}
		user.setActive(true);
		user.setEmail(userDTO.getEmail());
		user.setMobile_number(userDTO.getMobile_number());
		user.setName(userDTO.getName());
		user.setRole("USER");
		user.setPassword(userDTO.getPassword());
		System.out.println(userDTO);
		user = userRepository.save(user);
		userDTO.setId(user.getId());
		Address address = userDTO.getAddress();
		address.setUser(user);
		addressRepository.save(address);
		return userDTO;


	}
//
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder(12);
//	}

}
