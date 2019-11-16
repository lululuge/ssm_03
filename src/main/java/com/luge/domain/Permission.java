package com.luge.domain;

import java.io.Serializable;
import java.util.List;

/**
 * permission表的实体类
 */
public class Permission implements Serializable {
    private Integer id;
    private String permissionName;
    private String url;
    private List<Role> roles; // 权限表关联到的角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
