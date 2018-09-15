package com.blog.mapper;


import com.blog.vo.Category;
import com.blog.vo.CategoryCustom;
import com.blog.vo.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类mapper
 *
 * @author NanCheung
 */
@Mapper
public interface CategoryMapper {
    
    /**
     * 初始化分类信息
     *
     * @return
     */
    List<CategoryCustom> initCategoryList();
    
    /**
     * 通过id获取分类
     *
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);
    
    /**
     * 分页获取文章列表
     *
     * @param pager        分页对象
     * @param categoryName 分类名称
     * @return
     */
    List<Category> loadCategory(@Param("pager") Pager pager, @Param("categoryName") String categoryName);
    
    /**
     * 检查是否存在
     *
     * @param category
     * @return
     */
    int checkExist(Category category);
    
    /**
     * 保存分类
     *
     * @param category
     */
    void saveCategory(Category category);
    
    /**
     * 更新分类
     *
     * @param category
     */
    void updateCategory(Category category);
    
    /**
     * 初始化分页信息
     *
     * @param pager 分页对象
     * @return
     */
    int initPage(Pager pager);
    
    /**
     * 获取分类集合
     *
     * @return
     */
    List<Category> getCategoryList();
    
    /**
     * 获取当前id的文章数量
     *
     * @param categoryId
     * @return
     */
    int articleCatePage(int categoryId);
    
    /**
     * 删除分类
     *
     * @param categoryId 分类id
     */
    void deleteCategoryById(Integer categoryId);
}
