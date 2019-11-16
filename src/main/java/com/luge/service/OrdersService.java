package com.luge.service;

import com.luge.domain.Orders;

import java.util.List;

/**
 * 订单的业务层接口
 */
public interface OrdersService {
    /**
     * 查询所有订单
     * @return
     */
   public List<Orders> findAll(Integer currentPage, Integer pageSize);

    /**
     * 删除选中订单
     * @param ids
     */
    void delete(String[] ids);

    /**
     *
     * 根据id查询订单详细信息
     * @param id
     */
    Orders findById(Integer id);
}
