package com.litmus7.training.ecommerce.backend.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ProductCategoryController.class)
class ProductCategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAllProductsCategoryPositivetest() throws Exception {
		// when(productService.retrieveAllProducts()).thenReturn(expectedResult);
		String expectedJson = "[{\"productID\":1,\"productName\":\"Mobile\",\"price\":29.0},{\"productID\":2,\"productName\":\"Car\",\"price\":700.0},{\"productID\":3,\"productName\":\"Bike\",\"price\":67.4},{\"productID\":4,\"productName\":\"Toy\",\"price\":98.0},{\"productID\":5,\"productName\":\"Pen\",\"price\":4.5}]";

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/category")).andExpect(status().isOk())
				.andReturn();
		String actualJson = result.getResponse().getContentAsString();
		assertTrue(expectedJson.equals(actualJson));

	}

}
