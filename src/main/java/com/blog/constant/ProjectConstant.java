package com.blog.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置项目常量
 *
 * @author NanCheung
 */
@Component
public class ProjectConstant {
    
    /**
     * 域名
     */
    public static final String domain = "http://blog.nancheung.com/";
    
    /**
     * 设置字符编码
     */
    public static final String CHARSET = "utf-8";
    
    /**
     * UserInfo名称
     */
    public static final String USER_INFO = "userInfo";
}
