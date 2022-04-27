package com.litmus7.training.ecommerce.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "address")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private String name;

	private String address_name;
	private String city;
	private String state;
	private String country;
	private String pincode;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser user;



	public Long getId() {
		return id;
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

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Long getUser() {
		return user.getId();
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Address() {
		super();

	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", address_name=" + address_name + ", city=" + city + ", state="
				+ state + ", country=" + country + ", pincode=" + pincode + ", user=" + user
				+ "]";
	}

}
