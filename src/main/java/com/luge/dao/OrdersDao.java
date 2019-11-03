package com.luge.dao;

import com.luge.domain.Member;
import com.luge.domain.Orders;
import com.luge.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单的dao接口
 */
@Repository
public interface OrdersDao {
    /**
     * 查询所有订单
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
//            查询出产品信息
            @Result(column = "productId", property = "product", javaType = Product.class, one=@One(select = "com.luge.dao.ProductDao.findById"))
////            查询出会员信息
//            @Result(column = "memberId", property = "member", one = @One(select = "com.luge.dao.MemberDao.findById2"))
    })
    List<Orders> findAll();

    /**
     * 删除选中订单
     * @param id
     */
    @Update("delete from orders where id = #{id}")
    void delete(String id);

    /**
     * 根据id查询订单详细信息
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
//            查询出产品信息
            @Result(column = "productId", property = "product", javaType = Product.class, one=@One(select = "com.luge.dao.ProductDao.findById")),
            //            查询出会员信息
            @Result(column = "memberId", property = "member", javaType = Member.class, one = @One(select = "com.luge.dao.MemberDao.findById2")),
//            查询出游客信息
            @Result(column = "id", property = "travellers", javaType = java.util.List.class, many = @Many(select = "com.luge.dao.TravellerDao.findById"))
    })
    Orders findById(int id);
}
