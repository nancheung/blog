package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.PagerMapper;
import com.blog.service.PagerService;
import com.blog.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分页实现类
 * @author NanCheung
 */
@Service
public class PagerServiceImpl implements PagerService {

    private final ArticleMapper articleMapper;
    private final PagerMapper pagerMapper;
    
    @Autowired
    public PagerServiceImpl(ArticleMapper articleMapper, PagerMapper pagerMapper) {
        this.articleMapper = articleMapper;
        this.pagerMapper = pagerMapper;
    }
    
    @Override
    public void initPage(Pager pager) {
        int count = articleMapper.getArticleCount();
        pager.setTotalCount(count);
    }

    @Override
    public void loadCategoryPager(Pager pager,Integer categoryId) {
        int count = pagerMapper.loadCategoryPager(categoryId);
        pager.setTotalCount(count);
    }

    @Override
    public void loadTagPager(Pager pager, Integer tagId) {
        int count = pagerMapper.loadTagPager(tagId);
        pager.setTotalCount(count);
    }

    @Override
    public void loadArchivePager(Pager pager, String createTime) {
        int count = pagerMapper.loadArchivePager(createTime);
        pager.setTotalCount(count);
    }
}
