package com.blog.controller.admin;

import com.blog.constant.ProjectConstant;
import com.blog.service.UserService;
import com.blog.util.Md5Util;
import com.blog.util.ResultInfo;
import com.blog.util.ResultInfoFactory;
import com.blog.vo.User;
import com.blog.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author NanCheung
 */
@Controller
public class UserController {
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param request     确认密码
     */
    @RequestMapping("/admin/password/update")
    @ResponseBody
    public ResultInfo updatePassword(String oldPassword, String newPassword, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (!Md5Util.pwdDigest(oldPassword).equals(user.getPassword())) {
            return ResultInfoFactory.getErrorResultInfo("原密码错误！！！");
        }
        user.setPassword(Md5Util.pwdDigest(newPassword));
        userService.updatePassword(user);
        return ResultInfoFactory.getSuccessResultInfo("修改成功！！！");
    }
    
    /**
     * 获取用户信息
     *
     * @param model 对象
     */
    @RequestMapping("/admin/userinfo/get")
    public String getUserInfo(Model model) {
        UserInfo userInfo = userService.getUserInfo();
        model.addAttribute(ProjectConstant.USER_INFO, userInfo);
        return "admin/partial/userinfo";
        
    }
    
    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息对象
     */
    @RequestMapping("/admin/userinfo/update")
    @ResponseBody
    public ResultInfo updateInfo(UserInfo userInfo) {
        userService.updateUserInfo(userInfo);
        return ResultInfoFactory.getSuccessResultInfo("更新个人信息成功!!");
    }
    
}


