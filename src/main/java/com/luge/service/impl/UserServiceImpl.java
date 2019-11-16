package com.luge.service.impl;

import com.github.pagehelper.PageHelper;
import com.luge.dao.UserDao;
import com.luge.domain.Role;
import com.luge.domain.UserInfo;
import com.luge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登录
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        System.out.println(userInfo);
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus() != 0, true, true, true,
                getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = null;
        for (Role role : roles) {
             list = new ArrayList<SimpleGrantedAuthority>();
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    /**
     * 查询所有用户
     */
    @Override
    public List<UserInfo> findAll(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize); // 不要忘记此步骤
        return userDao.findAll(currentPage, pageSize);
    }

    /**
     * 保存新用户
     */
    @Override
    public void save(UserInfo userInfo) {
        // 对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    /**
     * 根据id查询单个用户
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(Integer id) {
        return userDao.findById(id);
    }

    /**
     * 根据id查询当前用户可添加的角色
     * @param id
     * @return
     */
    @Override
    public List<Role> findOtherRoles(Integer id) {
        return userDao.findOtherRoles(id);
    }

    /**
     * 给用户添加角色
     */
    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) {
        for (Integer roleId : roleIds) {
            userDao.addRoleToUser(userId, roleId);
        }
    }
}
