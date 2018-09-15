package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.TagMapper;
import com.blog.service.TagService;
import com.blog.vo.ArticleCustom;
import com.blog.vo.Pager;
import com.blog.vo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签实现类
 *
 * @author NanCheung
 */
@Service
public class TagServiceImpl implements TagService {
    private final ArticleMapper articleMapper;
    private final TagMapper tagMapper;
    
    @Autowired
    public TagServiceImpl(ArticleMapper articleMapper, TagMapper tagMapper) {
        this.articleMapper = articleMapper;
        this.tagMapper = tagMapper;
    }
    
    @Override
    public List<ArticleCustom> loadArticleByTag(Pager pager, Integer tagId) {
        return articleMapper.loadArticleByTag(pager, tagId);
    }
    
    @Override
    public int getTagCount() {
        return tagMapper.getTagCount();
    }
    
    @Override
    public Tag getTagById(Integer id) {
        return tagMapper.getTagById(id);
    }
    
    @Override
    public List<Tag> loadTagList(Pager pager, String tagName) {
        pager.setStart(pager.getStart());
        return tagMapper.loadTagList(pager, tagName);
    }
    
    @Override
    public void saveTag(Tag tag) {
        tagMapper.saveTag(tag);
    }
    
    @Override
    public boolean checkExist(Tag tag) {
        int count = tagMapper.checkExist(tag);
        return count > 0;
    }
    
    @Override
    public void updateTag(Tag tag) {
        tagMapper.updateTag(tag);
    }
    
    @Override
    public void initPage(Pager pager) {
        int count = tagMapper.initPage(pager);
        pager.setTotalCount(count);
    }
    
    @Override
    public List<Tag> getTagList() {
        return tagMapper.getTagList();
    }
    
    
    /**
     * 初始化tag -> article的分页
     *
     * @param pager 分页对象
     */
    @Override
    public void articleTagPage(Pager pager, int tagId) {
        int count = tagMapper.articleTagPage(tagId);
        pager.setTotalCount(count);
    }
    
    @Override
    public void deleteTagById(int id) {
        tagMapper.deleteTagById(id);
        tagMapper.deleteArticleTagById(id);
    }
}
