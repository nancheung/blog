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
 * 文章归档的controller
 *
 * @author NanCheung
 */
@Controller
@RequestMapping("/archive")
public class ArchiveController {
    
    private final CategoryService categoryService;
    
    @Autowired
    public ArchiveController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    /**
     * 文章归档列表
     *
     * @param createTime 创建时间
     * @param pager      分页对象
     * @param model      对象
     */
    @RequestMapping("/load/{createTime}")
    public String categoryList(@PathVariable String createTime, Pager pager, Model model) {
        List<ArticleCustom> articleList = categoryService.loadArticleByArchive(createTime, pager);
        if (articleList != null && !articleList.isEmpty()) {
            model.addAttribute("articleList", articleList);
            model.addAttribute("pager", pager);
            model.addAttribute("createTime", createTime);
        }
        return "blog/part/archiveSummary";
    }
}