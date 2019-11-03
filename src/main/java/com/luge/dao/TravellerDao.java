package com.luge.dao;

import com.luge.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {

    /**
     * 根据订单id查询所有的游客信息
     * @param orderId
     * @return
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{orderId})")
    List<Traveller> findById(int orderId);
}
