package com.blog.vo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 自定义
 *
 * @author NanCheung
 */
@Alias("categoryCustom")
public class CategoryCustom implements Serializable {
    
    private static final long serialVersionUID = 1882274877093702018L;
    private Integer id;
    
    /**
     * 分类名
     */
    private String categoryName;
    
    /**
     * 分类别名
     */
    private String aliasName;
    
    private Integer sort;
    
    /**
     * 此分类下文章的数量
     */
    private Integer articleCount;
    
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
    
    public Integer getArticleCount() {
        return articleCount;
    }
    
    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }
    
    @Override
    public String toString() {
        return "CategoryCustom{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", aliasName='" + aliasName + '\'' +
                ", sort=" + sort +
                ", articleCount=" + articleCount +
                '}';
    }
}
