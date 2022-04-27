package com.litmus7.training.ecommerce.backend.dto;

import com.litmus7.training.ecommerce.backend.entity.Address;

public class UserDTO {
	private Long id;
	private String name;
	private String email;
	private String password;
	private Long mobile_number;
	private Address address;

	public Long getId() {
		return id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}


	public UserDTO() {
		super();
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", mobile_number=" + mobile_number + ", address=" + address + "]";
	}


}
