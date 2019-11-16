package com.luge.service.impl;

import com.github.pagehelper.PageHelper;
import com.luge.dao.ProductDao;
import com.luge.domain.Product;
import com.luge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(Integer currentPage, Integer pageSize) throws Exception {
        PageHelper.startPage(currentPage, pageSize);
        return productDao.findAll(currentPage, pageSize);
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public void delete(String[] ids) throws Exception {
        for (String id : ids) {
            productDao.delete(id);
        }
    }
}
