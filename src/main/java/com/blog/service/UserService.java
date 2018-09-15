package com.blog.service;


import com.blog.vo.User;
import com.blog.vo.UserInfo;

/**
 * 用户Service
 *
 * @author NanCheung
 */
public interface UserService {
    
    
    User loadUserByUsername(String username);
    
    UserInfo getUserInfo();
    
    void updateAvatar(String url, String username);
    
    void updatePassword(User user);
    
    void updateUserInfo(UserInfo userInfo);
    
    User getCurrentUser();
}
