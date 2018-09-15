package com.blog.controller;

import com.blog.service.CategoryService;
import com.blog.vo.ArticleCustom;
import com.blog.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 展示的分类controller
 *
 * @author NanCheung
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {
    
    private final CategoryService categoryService;
    
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    /**
     * 获取某个标签的分页文章
     *
     * @param model      对象
     * @param pager      分页对象
     * @param categoryId 分类ID
     */
    @RequestMapping("/load/{categoryId}")
    public String loadArticleByCategory(Model model, Pager pager, @PathVariable Integer categoryId) {
        List<ArticleCustom> articleList = categoryService.loadArticleByCategory(pager, categoryId);
        if (!articleList.isEmpty()) {
            model.addAttribute("articleList", articleList);
            model.addAttribute("pager", pager);
            model.addAttribute("categoryName", articleList.get(0).getCategoryName());
        }
        return "blog/part/categorySummary";
    }
    
    
}