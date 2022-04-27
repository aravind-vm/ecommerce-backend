package com.litmus7.training.ecommerce.backend.controller;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000/", "http://localhost:3001/" })
@RestController
@RequestMapping("/api/images")
public class ImageController {
	@Autowired
	ServletContext servletContext;

	@GetMapping(value = "/category/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getProductCategoryImage(@PathVariable String image) throws IOException {
		ClassPathResource imageFile = new ClassPathResource("/images/category_images/" + image);

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());
		return imageBytes;
	}

	@GetMapping(value = "/product/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getProductImage(@PathVariable String image) throws IOException {
		ClassPathResource imageFile = new ClassPathResource("/images/product_images/" + image);

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());
		return imageBytes;
	}

	@GetMapping(value = "/sku/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getPSkuImage(@PathVariable String image) throws IOException {
		ClassPathResource imageFile = new ClassPathResource("/images/sku_images/" + image);

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());
		return imageBytes;
	}
}

