package com.litmus7.training.ecommerce.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.litmus7.training.ecommerce.backend.dao.ProductRepository;
import com.litmus7.training.ecommerce.backend.dto.ProductDTO;
import com.litmus7.training.ecommerce.backend.entity.Product;
import com.litmus7.training.ecommerce.backend.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ProductCategoryService productCategoryService;


	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productRepo.findAll();
		return Optional.ofNullable(products.isEmpty() ? null : products).orElseThrow(
				() -> new ProductNotFoundException("ProductNotFoundException: Product list is empty at server side"));
	}

	@Override
	public List<Product> getAllActiveProducts(boolean b) {
		List<Product> products = productRepo.findByActive(b);
		return Optional.ofNullable(products.isEmpty() ? null : products).orElseThrow(
				() -> new ProductNotFoundException("ProductNotFoundException: Product list is empty at server side"));
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		Optional<Product> product = Optional.ofNullable(productRepo.getById(id));
		if (product.isEmpty()) {
			throw new ProductNotFoundException("ProductNotFoundException: Product not found for id: " + id);
		}
		return product;
	}
	@Override
	public Page<Product> getProductsByCategory(Long id, Integer pageno) {
		Page<Product> products = productRepo.findByCategoryIdAndActive(id, true, PageRequest.of(pageno, 25));
		return products;
	}

	@Override
	public Page<Product> getProductsByName(String name, Integer pageno) {

		return productRepo.findByNameContainingAndActive(name, true, PageRequest.of(pageno, 10));
	}

	@Override
	public Product addProduct(ProductDTO productDTO) {
		// validations
		Product product = new Product();
		product.setActive(true);
		product.setCategory(productCategoryService.getProducCategorytById(productDTO.getCategoryId()).get());
		product.setDescription(productDTO.getDescription());
		product.setImageUrl(productDTO.getImageUrl());
		product.setName(productDTO.getName());

		return productRepo.save(product);
	}

	@Override
	public Product updateProduct(Product product) {



		return productRepo.save(product);
	}

	@Override
	public String deleteProduct(Long id) {
		Optional.of(productRepo.getById(id)).orElseThrow(
				() -> new ProductNotFoundException("ProductNotFoundException: Product with id:" + id + " not found"));
		productRepo.deleteById(id);
		return "DELETE_SUCCESS";
	}

	@Override
	public List<Product> addAllProduct(@Valid List<ProductDTO> productDTOs) {
		List<Product> products = new ArrayList<Product>();
		for (ProductDTO productDTO : productDTOs) {
			Product product = new Product();
			product.setActive(true);
			product.setCategory(productCategoryService.getProducCategorytById(productDTO.getCategoryId()).get());
			product.setDescription(productDTO.getDescription());
			product.setImageUrl(productDTO.getImageUrl());
			product.setName(productDTO.getName());
			products.add(product);

		}

		return productRepo.saveAll(products);
	}

}
