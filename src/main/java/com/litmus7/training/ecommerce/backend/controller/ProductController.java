package com.litmus7.training.ecommerce.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litmus7.training.ecommerce.backend.dto.ProductDTO;
import com.litmus7.training.ecommerce.backend.entity.Product;
import com.litmus7.training.ecommerce.backend.service.ProductService;

@CrossOrigin(origins = { "http://localhost:3000/", "http://localhost:3001/" })
@RestController
@RequestMapping("api/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("")
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProductById(id));

	}

	@GetMapping("/active")
	public ResponseEntity<List<Product>> getAllValidProducts() {
		return ResponseEntity.ok(productService.getAllActiveProducts(true));


	}

	@GetMapping("/category/{id}/page/{pageno}")
	public ResponseEntity<Page<Product>> getProductByCategory(@PathVariable Long id, @PathVariable Integer pageno) {
		return ResponseEntity.ok(productService.getProductsByCategory(id, pageno));

	}

	@GetMapping("/{name}/{pageno}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable String name, @PathVariable Integer pageno) {
		productService.getProductsByName(name, pageno);
		return null;
	}

	@PostMapping("")
	public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductDTO productDTO) {
		return new ResponseEntity<Product>(productService.addProduct(productDTO), HttpStatus.CREATED);

	}

	@PostMapping("/createAll")
	public ResponseEntity<List<Product>> addAllProduct(@RequestBody @Valid List<ProductDTO> productDTOs) {
		return new ResponseEntity<List<Product>>(productService.addAllProduct(productDTOs), HttpStatus.CREATED);

	}

	@PutMapping("")
	public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product) {
		productService.updateProduct(product);
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return null;
	}

}
