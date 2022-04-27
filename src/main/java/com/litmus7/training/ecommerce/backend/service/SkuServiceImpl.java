package com.litmus7.training.ecommerce.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litmus7.training.ecommerce.backend.dao.SkuRepository;
import com.litmus7.training.ecommerce.backend.dto.OrderItemDTO;
import com.litmus7.training.ecommerce.backend.dto.SkuDTO;
import com.litmus7.training.ecommerce.backend.entity.OrderItem;
import com.litmus7.training.ecommerce.backend.entity.Sku;
import com.litmus7.training.ecommerce.backend.exception.SkuNotFoundException;

@Service
public class SkuServiceImpl implements SkuService {

	@Autowired
	private SkuRepository skuRepo;
	@Autowired
	private ProductService productService;


	@Override
	public List<Sku> getAllSku() {
		List<Sku> skus = skuRepo.findAll();
		return Optional.ofNullable(skus.isEmpty() ? null : skus)
				.orElseThrow(() -> new SkuNotFoundException("SkuNotFoundException: Sku list is empty at server side"));
	}

	@Override
	public List<Sku> getAllActiveSku(boolean b) {
		List<Sku> skus = skuRepo.findByActive(b);
		return Optional.ofNullable(skus.isEmpty() ? null : skus)
				.orElseThrow(() -> new SkuNotFoundException("SkuNotFoundException: Sku list is empty at server side"));
	}

	@Override
	public Optional<Sku> getSkuById(Long id) {
		Optional<Sku> sku = Optional.ofNullable(skuRepo.getById(id));
		if (sku.isEmpty()) {
			throw new SkuNotFoundException("SkuNotFoundException: Sku not found for id: " + id);
		}
		return sku;
	}
	@Override
	public List<Sku> getSkuByProduct(Long id) {
		List<Sku> skus = skuRepo.findByProductIdAndActive(id, true);
		return skus;
	}


	@Override
	public Sku addSku(SkuDTO skuDTO) {
		// validations
		Sku sku = new Sku();
		sku.setActive(true);
		sku.setUnitPrice(skuDTO.getUnitPrice());
		sku.setProduct(productService.getProductById(skuDTO.getProductId()).get());
		sku.setImageUrl(skuDTO.getImageUrl());
		sku.setStock_quantity(skuDTO.getStock_quantity());
		sku.setAvailable_quantity(skuDTO.getStock_quantity());
		sku.setReserved_quantity(0L);
		sku.setColour(skuDTO.getColour());
		sku.setRam(skuDTO.getRam());
		sku.setStorage(skuDTO.getStorage());

		return skuRepo.save(sku);
	}

	public List<Sku> addAllSku(List<SkuDTO> skuDTOs) {
		// validations
		List<Sku> skus = new ArrayList<Sku>();
		for (SkuDTO skuDTO : skuDTOs) {
			Sku sku = new Sku();
			sku.setActive(true);
			sku.setUnitPrice(skuDTO.getUnitPrice());
			sku.setProduct(productService.getProductById(skuDTO.getProductId()).get());
			sku.setImageUrl(skuDTO.getImageUrl());
			sku.setStock_quantity(skuDTO.getStock_quantity());
			sku.setAvailable_quantity(skuDTO.getStock_quantity());
			sku.setReserved_quantity(0L);
			sku.setColour(skuDTO.getColour());
			sku.setRam(skuDTO.getRam());
			sku.setStorage(skuDTO.getStorage());
			skus.add(sku);
		}

		return skuRepo.saveAll(skus);
	}

	@Override
	public Sku updateSku(SkuDTO skuDTO) {
		Sku sku = skuRepo.getById(skuDTO.getId());
		sku.setActive(skuDTO.getActive());
		sku.setUnitPrice(skuDTO.getUnitPrice());
		sku.setAvailable_quantity(skuDTO.getAvailable_quantity());
		sku.setReserved_quantity(skuDTO.getReserved_quantity());
		sku.setStock_quantity(skuDTO.getStock_quantity());
		return skuRepo.save(sku);
	}

	@Override
	public String deleteSku(Long id) {
		Optional.of(skuRepo.getById(id))
				.orElseThrow(() -> new SkuNotFoundException("SkuNotFoundException: Sku with id:" + id + " not found"));
		skuRepo.deleteById(id);
		return "DELETE_SUCCESS";
	}

	public OrderItemDTO updateSkuQuantityOnNewItem(OrderItemDTO item) {
		Sku sku = getSkuById(item.getSkuId()).get();
		if (1 <= sku.getAvailable_quantity()) {
			sku.setAvailable_quantity(sku.getAvailable_quantity() - 1);
			sku.setReserved_quantity(sku.getReserved_quantity() + 1);
			item.setFullfilledStockQty(item.getQuantity());
			item.setStockStatus(1);

		} else if (sku.getAvailable_quantity() == 0) {
			item.setFullfilledStockQty(0);
			item.setStockStatus(2);
		}
		skuRepo.save(sku);
		return item;
	}



	public OrderItemDTO updateSkuQuantityOnOldItems(OrderItemDTO item) {
		Sku sku = getSkuById(item.getSkuId()).get();
		if (1 <= sku.getAvailable_quantity()) {
			sku.setAvailable_quantity(sku.getAvailable_quantity() - 1);
			sku.setReserved_quantity(sku.getReserved_quantity() + 1);
			item.setFullfilledStockQty(item.getFullfilledStockQty() + 1);
			item.setStockStatus(1);
			skuRepo.save(sku);
			return item;
		} else if (sku.getAvailable_quantity() == 0) {
			item.setStockStatus(2);
			return item;
		}
		return item;

	}

	public OrderItemDTO updateSkuQuantityOnOldItemsMinus(OrderItemDTO item) {
		Sku sku = getSkuById(item.getSkuId()).get();

		sku.setAvailable_quantity(sku.getAvailable_quantity() + 1);
		sku.setReserved_quantity(sku.getReserved_quantity() - 1);
		item.setFullfilledStockQty(item.getFullfilledStockQty() - 1);
		item.setStockStatus(1);
		skuRepo.save(sku);
		return item;

	}

	public void skuCheckout(Set<OrderItem> items) {
		for (OrderItem item : items) {
			Sku sku = getSkuById(item.getSku_id()).get();
			sku.setReserved_quantity(sku.getReserved_quantity() - item.getQuantity());
			sku.setStock_quantity(sku.getStock_quantity() - item.getQuantity());
			skuRepo.save(sku);

		}

	}

	public void skuCheckoutCancel(Set<OrderItem> items) {
		for (OrderItem item : items) {
			Sku sku = getSkuById(item.getSku_id()).get();
			sku.setReserved_quantity(sku.getReserved_quantity() - item.getQuantity());
			sku.setAvailable_quantity(sku.getAvailable_quantity() + item.getQuantity());
			skuRepo.save(sku);
		}

	}

}
