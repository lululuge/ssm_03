package com.luge.controller;

import com.github.pagehelper.PageInfo;
import com.luge.domain.Role;
import com.luge.domain.UserInfo;
import com.luge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService = null;

    /**
     * 查询所有用户(未分页)
     * @return
     */
    /*@RequestMapping(path = "/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mav = new ModelAndView();
        List<UserInfo> users = userService.findAll();
        mav.addObject("userList", users);
        mav.setViewName("user-list");
        return mav;
    }*/

    /**
     * 查询所有用户（分页）
     */
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "currentPage", required = true, defaultValue = "1") Integer currentPage,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer pageSize) {
        ModelAndView mav = new ModelAndView();
        List<UserInfo> users = userService.findAll(currentPage, pageSize);
        PageInfo pageInfo = new PageInfo(users);
        mav.addObject("pageInfo", pageInfo);
        mav.setViewName("user-page-list");
        return mav;
    }

    /**
     * 保存新用户
     */
    @RequestMapping(path = "/save.do")
    public String save (UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 根据id查询单个用户信息
     */
    @RequestMapping(path = "/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer id) {
        UserInfo userInfo = userService.findById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", userInfo);
        mav.setViewName("user-show");
        return mav;
    }

    /**
     * 根据id查询用户以及该用户可添加的角色
     */
    @RequestMapping(path = "/findUserByIdAndAddableRole.do")
    public ModelAndView findUserByIdAndAddableRole(@RequestParam(name = "id", required = true) Integer id) {
//        System.out.println("id是" + id);
        ModelAndView mav = new ModelAndView();
        // 根据id查询出用户信息
        UserInfo userInfo = userService.findById(id);
        // 根据id查询出可添加的角色
        List<Role> roles = userService.findOtherRoles(id);
        mav.addObject("user", userInfo);
        mav.addObject("roleList", roles);
        mav.setViewName("user-role-add");
        return mav;
    }

    /**
     * 用户添加角色
     */
    @RequestMapping(path = "/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) Integer userId,
                                @RequestParam(name = "ids", required = true) Integer[] roleIds) {
//        String userId = request.getParameter("userId");
//        String[] roleIds = request.getParameterValues("ids");
        System.out.println("userId是" + userId);
        System.out.println(roleIds[0]);
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

}
