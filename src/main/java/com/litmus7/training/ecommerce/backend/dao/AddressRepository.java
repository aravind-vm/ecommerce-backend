package com.litmus7.training.ecommerce.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litmus7.training.ecommerce.backend.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
