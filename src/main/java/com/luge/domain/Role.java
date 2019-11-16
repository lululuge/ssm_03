package com.luge.domain;

import java.io.Serializable;
import java.util.List;

/**
 * role表的实体类
 */
public class Role implements Serializable {
    private Integer id;
    private String roleName;
    private String roleDesc;
    private List<UserInfo> users; // 角色表关联到的用户
    private List<Permission> permissions; // 角色表关联到的权限

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
