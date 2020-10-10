package com.fourfaith.RemoteTopUp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.RemoteTopUp.dao.ProductMapper;
import com.fourfaith.RemoteTopUp.model.Product;
import com.fourfaith.RemoteTopUp.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return productMapper.deleteByPrimaryKey(id);
	}
 
	@Override
	public int insert(Product record) {
		return productMapper.insert(record);
	}

	@Override
	public int insertSelective(Product record) {
		return productMapper.insertSelective(record);
	}

	@Override
	public Product selectByPrimaryKey(String id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Product record) {
		return productMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Product record) {
		return productMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Product> getProducts() {
		return productMapper.getProducts();
	}

}
