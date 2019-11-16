package com.luge.dao;

import com.luge.domain.Permission;
import com.luge.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface RoleDao {
    /**
     * 根据userId查询用户所关联的角色信息以及角色所关联的权限信息
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from user_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.luge.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(Integer userId);

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 保存新角色
     * @param role
     */
    @Insert("insert into role (roleName, roleDesc) values (#{roleName}, #{roleDesc})")
    void save(Role role);

    /**
     * 根据roleId查询单个角色信息
     * @param roleId
     * @return
     */
    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.luge.dao.PermissionDao.findByRoleId"))
    })
    Role findById(int roleId);

    /**
     * 根据roleId查询可以添加的权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(Integer roleId);

    /**
     * 给角色添加权限
     */
    @Insert("insert into role_permission (roleId, permissionId) values (#{roleId}, #{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}
