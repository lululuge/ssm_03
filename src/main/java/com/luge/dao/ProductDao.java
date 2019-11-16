package com.luge.dao;

import com.luge.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品的dao接口
 */
@Repository
public interface ProductDao {
    /**
     * 查询所有产品
     * @return
     */
    @Select("select * from product")
    public List<Product> findAll(Integer currentPage, Integer pageSize) throws Exception;

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    public Product findById(Integer id);

    /**
     * 保存新产品
     * @param product
     */
    @Insert("insert into product (productNum, productName, cityName, departureTime, productPrice, productDesc, productStatus)" +
            "values (#{productNum}, #{productName}, #{cityName}, #{departureTime}, #{productPrice}, #{productDesc}, #{productStatus})")
    void save(Product product);

    /**
     * 删除选中产品
     * @param id
     */
    @Update("delete from product where id = #{id}")
    void delete(String id);
}
