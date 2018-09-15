package com.blog.controller;

import com.blog.service.PagerService;
import com.blog.service.TagService;
import com.blog.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 各页面初始化分页controller
 *
 * @author NanCheung
 */
@RestController
public class PagerController {
    
    /**
     * 分页的service
     */
    private final PagerService pagerService;
    
    /**
     * 标签的service
     */
    private final TagService tagService;
    
    @Autowired
    public PagerController(PagerService pagerService, TagService tagService) {
        this.pagerService = pagerService;
        this.tagService = tagService;
    }
    
    /**
     * 初始化文章分页信息
     */
    @RequestMapping("/pager/articles/load")
    public Pager loadArticlePager(Pager pager) {
        pager.setLimit(5);
        pagerService.initPage(pager);
        return pager;
    }
    
    /**
     * 初始化当前分类id的文章分页信息
     *
     * @param pager      分页对象 分页对象
     * @param categoryId 分类id
     */
    @RequestMapping("/pager/categories/{categoryId}")
    public Pager loadCategoryPager(Pager pager, @PathVariable Integer categoryId) {
        pagerService.loadCategoryPager(pager, categoryId);
        return pager;
    }
    
    /**
     * 初始化当前标签的文章分页信息
     *
     * @param pager 分页对象 分页对象
     * @param tagId 标签
     */
    @RequestMapping("/pager/tags/{tagId}")
    public Pager initPage(Pager pager, @PathVariable Integer tagId) {
        tagService.articleTagPage(pager, tagId);
        return pager;
    }
    
    @GetMapping("/pager/archive/{createTime}")
    public Pager loadArchivePager(Pager pager, @PathVariable String createTime) {
        pagerService.loadArchivePager(pager, createTime);
        return pager;
    }
    
}
