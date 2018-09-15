package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.CategoryMapper;
import com.blog.service.CategoryService;
import com.blog.vo.ArticleCustom;
import com.blog.vo.Category;
import com.blog.vo.CategoryCustom;
import com.blog.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分类实现类
 *
 * @author NanCheung
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryMapper categoryMapper;
    
    private final ArticleMapper articleMapper;
    
    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, ArticleMapper articleMapper) {
        this.categoryMapper = categoryMapper;
        this.articleMapper = articleMapper;
    }
    
    
    @Override
    public List<ArticleCustom> loadArticleByCategory(Pager pager, Integer categoryId) {
        pager.getStart();
        return articleMapper.loadArticleByCategory(pager, categoryId);
    }
    
    @Override
    public List<CategoryCustom> initCategoryList() {
        return categoryMapper.initCategoryList();
    }
    
    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }
    
    @Override
    public List<Category> loadCategory(Pager pager, String categoryName) {
        pager.setStart(pager.getStart());
        return categoryMapper.loadCategory(pager, categoryName);
    }
    
    @Override
    public boolean checkExist(Category category) {
        int count = categoryMapper.checkExist(category);
        return count > 0;
    }
    
    @Override
    public void saveCategory(Category category) {
        categoryMapper.saveCategory(category);
    }
    
    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }
    
    @Override
    public void initPage(Pager pager) {
        int count = categoryMapper.initPage(pager);
        pager.setTotalCount(count);
    }
    
    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }
    
    
    @Override
    public List<ArticleCustom> loadArticleByArchive(String createTime, Pager pager) {
        pager.getStart();
        return articleMapper.loadArticleByArchive(pager, createTime);
    }
    
    @Override
    public int getArticleCountByCategoryId(Integer categoryId) {
        return categoryMapper.articleCatePage(categoryId);
    }
    
    @Override
    public boolean deleteCategoryById(Integer categoryId) {
        categoryMapper.deleteCategoryById(categoryId);
        articleMapper.updateCategoryId(categoryId);
        return true;
    }
    
}
