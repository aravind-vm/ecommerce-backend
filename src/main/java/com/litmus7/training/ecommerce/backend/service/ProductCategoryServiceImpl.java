package com.litmus7.training.ecommerce.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.litmus7.training.ecommerce.backend.dao.ProductCategoryRepository;
import com.litmus7.training.ecommerce.backend.entity.ProductCategory;
import com.litmus7.training.ecommerce.backend.exception.ProductCategoryNameExistsException;
import com.litmus7.training.ecommerce.backend.exception.ProductCategoryNotFoundException;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepo;

	@Override
	public List<ProductCategory> getAllProductCategories() {
		return Optional.ofNullable(productCategoryRepo.findAll().isEmpty() ? null : productCategoryRepo.findAll())
				.orElseThrow(() -> new ProductCategoryNotFoundException("Product category at server is empty"));

	}
	@Override
	public Optional<ProductCategory> getProducCategorytById(Long id) {
		Optional<ProductCategory> productCategory = productCategoryRepo.findById(id);
		if (productCategory.isEmpty()) {
			throw new ProductCategoryNotFoundException(
					"ProductCategoryNotFoundException: Product category with id:" + id + " not found");

		}
		return productCategory;

		// return Optional.of(productCategoryRepo.getById(id)).orElseThrow(() -> new
		// ProductCategoryNotFoundException(
		// "ProductCategoryNotFoundException: Product category with id:" + id + " not
		// found"));
	}

	@Override
	public ProductCategory addProductCategory(ProductCategory productCategory) {
		for (ProductCategory category : productCategoryRepo.findAll()) {
			if (category.getCategoryName().contentEquals(productCategory.getCategoryName())) {
				throw new ProductCategoryNameExistsException("ProductCategoryNameExistsException: Category name "
						+ productCategory.getCategoryName() + " already exists");
			}
		}

		return productCategoryRepo.save(productCategory);
	}

	@Override
	public ProductCategory updateProductCategory(ProductCategory productCategory) {
		ProductCategory productC = productCategoryRepo.getById(productCategory.getId());
		productCategory.setDateCreated(productC.getDateCreated());
		return productCategoryRepo.save(productCategory);
	}

	@Override
	public String deleteProductCategory(Long id) {

		Optional.of(productCategoryRepo.getById(id)).orElseThrow(() -> new ProductCategoryNotFoundException(
				"ProductCategoryNotFoundException: Product category with id:" + id + " not found"));
		productCategoryRepo.deleteById(id);
		return "DELETE_SUCCESS";

	}

	@Override
	public Page<ProductCategory> getProductCategoriesByPage(Integer pageno) {
		return productCategoryRepo.findAll(PageRequest.of(pageno, 10));

	}




}
