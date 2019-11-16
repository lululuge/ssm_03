package com.luge.service;

import com.luge.domain.Permission;
import com.luge.domain.Role;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有角色
     */
    List<Role> findAll();

    /**
     * 保存新角色
     * @param role
     */
    void save(Role role);

    /**
     * 根据roleId查询单个角色信息
     * @param roleId
     * @return
     */
    Role findById(Integer roleId);

    /**
     * 根据roleId查询可以添加的权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermission(Integer roleId);

    /**
     * 给角色添加权限
     */
    void addPermissionToRole(Integer roleId, Integer[] permissionIds);
}
