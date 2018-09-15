package com.blog.mapper;


import com.blog.vo.Article;
import com.blog.vo.ArticleCustom;
import com.blog.vo.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章Mapper
 *
 * @author NanCheung
 */
@Mapper
public interface ArticleMapper {
    
    /**
     * 获取正常的所有文章的数量
     *
     * @return 正常的所有文章的数量
     */
    int getArticleCount();
    
    /**
     * 分页获取文章
     *
     * @param pager start,limit
     * @return 文章列表
     */
    List<ArticleCustom> getArticleList(Pager pager);
    
    /**
     * 通过分类获取所有的文章
     *
     * @param pager      start,limit
     * @param categoryId 分类ID
     * @return 文章列表
     */
    List<ArticleCustom> loadArticleByCategory(@Param("pager") Pager pager, @Param("categoryId") Integer categoryId);
    
    /**
     * 通过tagId分页获取当前tag下的文章列表
     *
     * @param pager start,limit
     * @param tagId 标签ID
     * @return 当前tag下的文章列表
     */
    List<ArticleCustom> loadArticleByTag(@Param("pager") Pager pager, @Param("tagId") Integer tagId);
    
    /**
     * 获取所有的文章数量
     *
     * @param pager start,limit
     * @return 所有的文章数量
     */
    int initPage(Pager pager);
    
    List<Article> loadArticle(Map<String, Object> param);
    
    void updateStatue(@Param("id") Integer id, @Param("status") int status);
    
    void saveArticle(Article article);
    
    void saveArticleTag(@Param("articleId") Integer articleId, @Param("tags") int[] tags);
    
    /**
     * 检查随机的id是否已经存在
     * @param id 随机的id
     * @return 为0则唯一
     */
    int checkExist(Integer id);
    
    Article getArticleById(Integer id);
    
    void updateArticle(Article article);
    
    void deleteArticleTag(Integer articleId);
    
    void deleteArticle(Integer id);
    
    ArticleCustom getArticleCustomById(Integer id);
    
    /**
     * 获取上一篇文章
     *
     * @param id
     * @return
     */
    Article getLastArticle(Integer id);
    
    /**
     * 获取下一篇文章
     *
     * @param articleId
     * @return
     */
    Article getNextArticle(Integer articleId);
    
    /**
     * 增加文章阅读数量
     *
     * @param articleId
     */
    void addArticleCount(Integer articleId);
    
    /**
     * 十篇受欢迎的文章
     *
     * @return
     */
    List<ArticleCustom> popularArticle();
    
    /**
     * 获取文章id数组
     *
     * @return
     */
    String[] getArticleId();
    
    /**
     * 通过关键字获取文章
     *
     * @param keyword
     * @return
     */
    List<Article> getArticleListByKeywords(@Param("keyword") String keyword);
    
    /**
     * 获取时间归档信息
     *
     * @return
     */
    List<Map> articleArchiveList();
    
    /**
     * 通过时间归档查询文章
     *
     * @param pager      分页对象
     * @param createTime 创建时间
     * @return
     */
    List<ArticleCustom> loadArticleByArchive(@Param("pager") Pager pager, @Param("createTime") String createTime);
    
    /**
     * 更新分类id
     * 设置为9999
     * 用于删除分类并且存在文章的时候
     *
     * @param categoryId
     */
    void updateCategoryId(Integer categoryId);
    
}
