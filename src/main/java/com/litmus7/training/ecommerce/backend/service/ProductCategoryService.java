package com.litmus7.training.ecommerce.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.litmus7.training.ecommerce.backend.entity.ProductCategory;

public interface ProductCategoryService {

	public List<ProductCategory> getAllProductCategories();

	public Optional<ProductCategory> getProducCategorytById(Long id);

	public ProductCategory addProductCategory(ProductCategory productCategory);

	public ProductCategory updateProductCategory(ProductCategory productCategory);

	public String deleteProductCategory(Long id);

	public Page<ProductCategory> getProductCategoriesByPage(Integer pageno);


}
