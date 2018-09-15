package com.blog.service;


import com.blog.vo.ArticleCustom;
import com.blog.vo.Category;
import com.blog.vo.CategoryCustom;
import com.blog.vo.Pager;

import java.util.List;

/**
 * 分类Service
 *
 * @author NanCheung
 */
public interface CategoryService {
    
    
    List<ArticleCustom> loadArticleByCategory(Pager pager, Integer categoryId);
    
    /**
     * 初始化分类信息
     *
     * @return
     */
    List<CategoryCustom> initCategoryList();
    
    Category getCategoryById(Integer id);
    
    List<Category> loadCategory(Pager pager, String categoryName);
    
    boolean checkExist(Category category);
    
    void saveCategory(Category category);
    
    void updateCategory(Category category);
    
    void initPage(Pager pager);
    
    List<Category> getCategoryList();
    
    
    List<ArticleCustom> loadArticleByArchive(String createTime, Pager pager);
    
    int getArticleCountByCategoryId(Integer categoryId);
    
    boolean deleteCategoryById(Integer categoryId);
}
