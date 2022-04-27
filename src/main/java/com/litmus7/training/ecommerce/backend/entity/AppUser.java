package com.litmus7.training.ecommerce.backend.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")

	@Size(min = 3, message = "name validation failed. Must contain atleast 3 letters for name")
	private String name;


	@Column(name = "active")
	private boolean active;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	private String email;
	private Long mobile_number;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Order> orders;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Address> addresses;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void addAddress(Address address) {
		if (addresses.isEmpty()) {
			addresses = new HashSet<Address>();
		}
		addresses.add(address);


	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}



	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public AppUser() {
		super();

	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", name=" + name + ", active=" + active + ", dateCreated=" + dateCreated
				+ ", lastUpdated=" + lastUpdated + ", email=" + email
				+ ", mobile_number=" + mobile_number + ", orders=" + orders + ", addresses=" + addresses + "]";
	}

}
