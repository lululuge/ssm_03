package com.luge.service;

import com.luge.domain.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 查询所有权限
     */
    public List<Permission> findAll();

    /**
     * 保存新权限
     * @param permission
     */
    void save(Permission permission);

    /**
     * 根据id删除权限
     * @param permissionId
     */
    void deleteById(Integer permissionId);
}
