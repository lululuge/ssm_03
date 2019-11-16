package com.luge.service;

import com.luge.domain.Role;
import com.luge.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * 查询所有用户
     */
    List<UserInfo> findAll(Integer currentPage, Integer pageSize);

    /**
     * 保存新用户
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 根据id查询单个用户
     * @param id
     * @return
     */
    UserInfo findById(Integer id);

    /**
     * 根据id查询当前用户可添加的角色
     * @param id
     * @return
     */
    List<Role> findOtherRoles(Integer id);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(Integer userId, Integer[] roleIds);
}
