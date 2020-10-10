package com.fourfaith.RemoteTopUp.service;

import java.util.List;

import com.fourfaith.RemoteTopUp.model.Product;

public interface ProductService {

	int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

	List<Product> getProducts();
}
