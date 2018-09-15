package com.blog.service.impl;

import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import com.blog.vo.User;
import com.blog.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户实现类
 *
 * @author NanCheung
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public User loadUserByUsername(String username) {
        return userMapper.getUser(username);
    }
    
    @Override
    public UserInfo getUserInfo() {
        return userMapper.getUserInfo();
    }
    
    @Override
    public void updateAvatar(String url, String username) {
        userMapper.updateAvatar(url, username);
    }
    
    @Override
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }
    
    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userMapper.updateUserInfo(userInfo);
    }
    
    @Override
    public User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}


