package com.luge.controller;

import com.luge.domain.Permission;
import com.luge.domain.Role;
import com.luge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     */
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll() {
        List<Role> roles = roleService.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("roleList", roles);
        mav.setViewName("role-list");
        return mav;
    }

    /**
     * 保存新角色
     */
    @RequestMapping(path = "/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 根据角色id查询单个角色信息
     */
    @RequestMapping(path = "/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer roleId) {
       Role role =  roleService.findById(roleId);
       ModelAndView mav = new ModelAndView();
       mav.addObject("role", role);
       mav.setViewName("role-show");
       return mav;
    }

    /**
     * 根据角色id查询角色以及可以添加的权限
     */
    @RequestMapping(path = "/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) Integer roleId) {
        ModelAndView mav = new ModelAndView();
        // 查询角色
        Role role = roleService.findById(roleId);
        // 查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermission(roleId);
        mav.addObject("role", role);
        mav.addObject("permissionList", otherPermissions);
        mav.setViewName("role-permission-add");
        return mav;
    }

    /**
     * 给角色添加权限
     */
    @RequestMapping(path = "/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) Integer roleId,
                                      @RequestParam(name = "ids", required = true) Integer[] permissionIds ) {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }


}
