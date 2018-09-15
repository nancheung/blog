package com.blog.controller;

import com.blog.service.TagService;
import com.blog.vo.ArticleCustom;
import com.blog.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 展示页面标签controller
 *
 * @author NanCheung
 */
@Controller
@RequestMapping("/tags")
public class TagController {
    
    private final TagService tagService;
    
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
    
    /**
     * 通过tag获取文章列表
     *
     * @param pager 分页对象
     * @param tagId 标签id
     * @param model 对象 数据视图
     */
    @RequestMapping("/load/{tagId}")
    public String loadArticleByTag(Pager pager, @PathVariable Integer tagId, Model model) {
        List<ArticleCustom> articleList = tagService.loadArticleByTag(pager, tagId);
        if (!articleList.isEmpty()) {
            model.addAttribute("articleList", articleList);
            model.addAttribute("pager", pager);
            model.addAttribute("tagName", tagService.getTagById(tagId).getTagName());
        }
        
        return "blog/part/tagSummary";
    }
    
}