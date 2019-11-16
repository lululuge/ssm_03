package com.luge.service.impl;

import com.luge.dao.PermissionDao;
import com.luge.domain.Permission;
import com.luge.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    /**
     * 查询所有权限
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    /**
     * 保存新权限
     */
    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    /**
     * 根据id删除权限
     */
    @Override
    public void deleteById(Integer permissionId) {
        // 首先根据权限id删除role_permission表中的数据
        permissionDao.deleteFromRole_permission(permissionId);
        // 然后根据权限id删除permission表中数据
        permissionDao.deleteById(permissionId);
    }
}
