package com.litmus7.training.ecommerce.backend.service;

import org.springframework.stereotype.Service;

import com.litmus7.training.ecommerce.backend.exception.PaymentFailureException;


@Service
public class PaymentServiceImpl {



	public boolean doPayement() {
		int i=(int)(Math.random()*10);
		if (i % 2 == 0) {
			throw new PaymentFailureException("Payment failure due to network error");
		} else
			return true;
		
	}
}
