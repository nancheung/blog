package com.blog.vo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 标签信息
 *
 * @author NanCheung
 */
@Alias("tag")
public class Tag implements Serializable {
    
    private static final long serialVersionUID = -2192282932391379983L;
    private Integer id;
    
    private String tagName; //标签名
    
    private String aliasName;  //别名
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTagName() {
        return tagName;
    }
    
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
    public String getAliasName() {
        return aliasName;
    }
    
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", aliasName='" + aliasName + '\'' +
                '}';
    }
}
