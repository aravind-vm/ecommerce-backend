package com.litmus7.training.ecommerce.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.litmus7.training.ecommerce.backend.dto.SkuDTO;
import com.litmus7.training.ecommerce.backend.entity.Sku;
import com.litmus7.training.ecommerce.backend.service.SkuService;

@CrossOrigin(origins = { "http://localhost:3000/", "http://localhost:3001/" })
@RestController
@RequestMapping("api/sku")
public class SkuController {
	@Autowired
	private SkuService skuService;

	@GetMapping("")
	public ResponseEntity<List<Sku>> getAllSkus() {
		return ResponseEntity.ok(skuService.getAllSku());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Sku>> getSkuById(@PathVariable Long id) {
		return ResponseEntity.ok(skuService.getSkuById(id));

	}

	@GetMapping("/active")
	public ResponseEntity<List<Sku>> getAllValidSku() {
		return ResponseEntity.ok(skuService.getAllActiveSku(true));

	}

	@GetMapping("/product/{id}")
	public ResponseEntity<List<Sku>> getProductByCategory(@PathVariable Long id) {
		return ResponseEntity.ok(skuService.getSkuByProduct(id));

	}


	@PostMapping("")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Sku> addSku(@RequestBody @Valid SkuDTO skuDTO) {
		return new ResponseEntity<Sku>(skuService.addSku(skuDTO), HttpStatus.CREATED);

	}

	@PostMapping("/createAll")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<List<Sku>> addAllSku(@RequestBody @Valid List<SkuDTO> skuDTOs) {
		return new ResponseEntity<List<Sku>>(skuService.addAllSku(skuDTOs), HttpStatus.CREATED);

	}

	@PutMapping("")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Sku> updateProduct(@RequestBody @Valid SkuDTO skuDTO) {
		skuService.updateSku(skuDTO);
		return null;
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteSku(@PathVariable Long id) {
		skuService.deleteSku(id);
		return null;
	}

}
