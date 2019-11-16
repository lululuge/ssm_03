package com.luge.dao;

import com.luge.domain.Role;
import com.luge.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.luge.dao.RoleDao.findByUserId"))
    })
    /**
     * 根据username查询用户
     */
    UserInfo findByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll(Integer currentPage, Integer pageSize);

    /**
     * 保存新用户
     * @param userInfo
     */
    @Insert("insert  into users (username, password, email, phoneNum, status) values (#{username}, #{password}, #{email}, #{phoneNum}, #{status})")
    void save(UserInfo userInfo);

    /**
     * 根据id查询单个用户
     * @param id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.luge.dao.RoleDao.findByUserId")),
    })
    UserInfo findById(Integer id);

    /**
     * 根据用户id查询当前用户可添加的角色
     * @param id
     * @return
     */
    @Select("select * from role where id not in (select roleId from user_role where userId = #{id})")
    List<Role> findOtherRoles(Integer id);

    /**
     * 给用户添加角色
     *
     */
    @Insert("insert into user_role (userId, roleId) values (#{userId}, #{roleId})")
    // 此处需要注意，当传入有多个参数时，需要把每个参数加上@param注解
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
