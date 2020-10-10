package com.fourfaith.RemoteTopUp.dao;

import java.util.List;

import com.fourfaith.RemoteTopUp.model.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    /**
     * @Title: getProducts
     * @方法描述: 查询所有商品
     * @param @return   
     * @return List<Product>  
     * @throws
     * @author 刘海年
     * @date 2018年8月14日-上午10:24:40
     */
	List<Product> getProducts();
}