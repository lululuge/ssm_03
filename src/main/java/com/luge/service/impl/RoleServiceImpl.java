package com.luge.service.impl;

import com.luge.dao.RoleDao;
import com.luge.domain.Permission;
import com.luge.domain.Role;
import com.luge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * 保存新角色
     */
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    /**
     * 根据roleId查询单个角色信息
     * @param roleId
     * @return
     */
    @Override
    public Role findById(Integer roleId) {
        return roleDao.findById(roleId);
    }

    /**
     * 根据roleId查询可以添加的权限
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findOtherPermission(Integer roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    /**
     * 给角色添加权限
     */
    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for (int permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }

    }
}
