package com.luge.dao;

import com.luge.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissionDao {
    /**
     * 根据角色id查询该角色对应权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findByRoleId(int roleId);

    /**
     * 查询所有权限
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * 保存新权限
     * @param permission
     */
    @Insert("insert into permission (permissionName, url) values (#{permissionName}, #{url})")
    void save(Permission permission);

    /**
     * 根据id删除权限
     * @param permissionId
     */
    @Delete("delete from role_permission where permissionId = #{permissionId}")
    void deleteFromRole_permission(int permissionId);
    @Delete("delete from permission where id = #{permissionId}")
    void deleteById(int permissionId);
}
