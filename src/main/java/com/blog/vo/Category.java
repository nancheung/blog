package com.blog.vo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 分类
 *
 * @author NanCheung
 */
@Alias("category")
public class Category implements Serializable {
    
    private static final long serialVersionUID = -2981993964013514554L;
    private Integer id;
    
    /**
     * 分类名称
     */
    private String categoryName;
    
    /**
     * 分类别名
     */
    private String aliasName;
    
    private Integer sort;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getAliasName() {
        return aliasName;
    }
    
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    
    public Integer getSort() {
        return sort;
    }
    
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", aliasName='" + aliasName + '\'' +
                ", sort=" + sort +
                '}';
    }
}
