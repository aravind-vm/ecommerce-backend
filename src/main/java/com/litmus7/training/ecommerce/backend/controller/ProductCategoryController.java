package com.litmus7.training.ecommerce.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litmus7.training.ecommerce.backend.entity.ProductCategory;
import com.litmus7.training.ecommerce.backend.service.ProductCategoryService;

@CrossOrigin(origins = { "http://localhost:3000/", "http://localhost:3001/" })
@RestController
@RequestMapping("/api/category")
public class ProductCategoryController {
	@Autowired
	ServletContext servletContext;

	@Autowired
	private ProductCategoryService productCategoryService;

	@GetMapping("")

	public ResponseEntity<List<ProductCategory>> getAllProductCategories() {

		return ResponseEntity.ok(productCategoryService.getAllProductCategories());
	}

	@GetMapping("/page/{pageno}")
	public ResponseEntity<Page<ProductCategory>> getProductCategoriesByPage(@PathVariable Integer pageno) {

		return ResponseEntity.ok(productCategoryService.getProductCategoriesByPage(pageno));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<ProductCategory>> getProducCategorytById(@PathVariable Long id) {

		return ResponseEntity.ok(productCategoryService.getProducCategorytById(id));

	}



	@PostMapping("")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<ProductCategory> addProductCategory(@RequestBody ProductCategory productCategory) {

		return new ResponseEntity<ProductCategory>(productCategoryService.addProductCategory(productCategory),
				HttpStatus.CREATED);
	}

	@PutMapping("")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory) {

		return new ResponseEntity<ProductCategory>(productCategoryService.addProductCategory(productCategory),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteProductCategory(@PathVariable Long id) {

		return ResponseEntity.ok(productCategoryService.deleteProductCategory(id));
	}

}
