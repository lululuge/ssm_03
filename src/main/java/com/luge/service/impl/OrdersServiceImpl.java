package com.luge.service.impl;

import com.github.pagehelper.PageHelper;
import com.luge.dao.OrdersDao;
import com.luge.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ordersService")
public class OrdersServiceImpl implements com.luge.service.OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return ordersDao.findAll();
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            ordersDao.delete(id);
        }
    }

    @Override
    public Orders findById(int id) {
        return ordersDao.findById(id);
    }
}
