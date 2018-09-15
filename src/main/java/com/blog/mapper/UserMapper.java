package com.blog.mapper;

import com.blog.vo.User;
import com.blog.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author NanCheung
 */
@Mapper
public interface UserMapper {
    /**
     * 获取用户凭证
     *
     * @param username 账号
     * @return
     */
    User getUser(@Param("username") String username);
    
    /**
     * 获取所有的用户
     *
     * @return
     */
    List<User> allUser();
    
    UserInfo getUserInfo();
    
    void updateAvatar(@Param("url") String url, @Param("username") String username);
    
    void updatePassword(User user);
    
    void updateUserInfo(UserInfo userInfo);
}
