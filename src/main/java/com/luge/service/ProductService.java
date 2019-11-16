package com.luge.service;

import com.luge.domain.Product;

import java.util.List;

/**
 * 产品的业务层接口
 */
public interface ProductService {
    /**
     * 查询所有产品
     * @return
     */
    public List<Product> findAll(Integer currentPage, Integer pageSize) throws Exception;

    /**
     * 保存新产品
     * @param product
     * @throws Exception
     */
    public void save(Product product) throws Exception;

    /**
     * 删除选中产品
     * @param ids
     * @throws Exception
     */
    public void delete(String[] ids) throws Exception;
}
