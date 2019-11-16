package com.luge.controller;

import com.luge.domain.Permission;
import com.luge.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     */
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll() {
        List<Permission> permissions = permissionService.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("permissionList", permissions);
        mav.setViewName("permission-list");
        return mav;
    }

    /**
     * 保存新权限
     */
    @RequestMapping(path = "/save.do")
    public String save(Permission permission) {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     * 根据id删除权限
     */
    @RequestMapping(path = "/deletePermission.do")
    public String deleteById(@RequestParam(name = "id", required = true) Integer permissionId) {
        permissionService.deleteById(permissionId);
        return "redirect:findAll.do";
    }
}
