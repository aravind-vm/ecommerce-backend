package com.litmus7.training.ecommerce.backend.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.litmus7.training.ecommerce.backend.dto.SkuDTO;
import com.litmus7.training.ecommerce.backend.entity.Sku;

public interface SkuService {

	public List<Sku> getAllSku();

	public List<Sku> getAllActiveSku(boolean b);

	public Optional<Sku> getSkuById(Long id);

	public List<Sku> getSkuByProduct(Long pId);

	public Sku addSku(SkuDTO skuDTO);

	public Sku updateSku(SkuDTO skuDTO);

	public String deleteSku(Long id);

	public List<Sku> addAllSku(@Valid List<SkuDTO> skuDTOs);

}
