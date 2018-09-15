package com.blog.vo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 用户详细信息
 *
 * @author NanCheung
 */
@Alias("userInfo")
public class UserInfo implements Serializable {
    
    private static final long serialVersionUID = -9021703588763524090L;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 头像src
     */
    private String avatar;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 电话号码
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 个性签名
     */
    private String signature;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 公告
     */
    private String announcement;
    
    /**
     * tg
     */
    private String telegram;
    
    /**
     * 微信
     */
    private String wechart;
    
    /**
     * github
     */
    private String github;
    
    /**
     * 格言（与君共勉）
     */
    private String motto;
    
    public String getMotto() {
        return motto;
    }
    
    public void setMotto(String motto) {
        this.motto = motto;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSignature() {
        return signature;
    }
    
    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAnnouncement() {
        return announcement;
    }
    
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
    
    public String getTelegram() {
        return telegram;
    }
    
    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }
    
    public String getWechart() {
        return wechart;
    }
    
    public void setWechart(String wechart) {
        this.wechart = wechart;
    }
    
    public String getGithub() {
        return github;
    }
    
    public void setGithub(String github) {
        this.github = github;
    }
    
    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", signature='" + signature + '\'' +
                ", address='" + address + '\'' +
                ", announcement='" + announcement + '\'' +
                ", telegram='" + telegram + '\'' +
                ", wechart='" + wechart + '\'' +
                ", github='" + github + '\'' +
                ", motto='" + motto + '\'' +
                '}';
    }
    
}
