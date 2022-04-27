package com.litmus7.training.ecommerce.backend.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.litmus7.training.ecommerce.backend.dao.UserCredentialsRepository;
import com.litmus7.training.ecommerce.backend.entity.UserCredentials;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserCredentialsRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCredentials user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("no username found");
		}
		System.out.println("####################################################");
		System.out.println(user);
		System.out.println("################################");
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

		return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
	}

}
