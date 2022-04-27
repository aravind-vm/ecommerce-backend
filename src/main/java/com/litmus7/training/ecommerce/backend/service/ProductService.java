package com.litmus7.training.ecommerce.backend.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.litmus7.training.ecommerce.backend.dto.ProductDTO;
import com.litmus7.training.ecommerce.backend.entity.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public List<Product> getAllActiveProducts(boolean b);

	public Optional<Product> getProductById(Long id);

	public Page<Product> getProductsByCategory(Long id, Integer pageno);

	public Page<Product> getProductsByName(String name, Integer pageno);

	public Product addProduct(ProductDTO productDTO);

	public Product updateProduct(Product product);

	public String deleteProduct(Long id);

	public List<Product> addAllProduct(@Valid List<ProductDTO> productDTOs);

}
