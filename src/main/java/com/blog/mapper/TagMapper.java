package com.blog.mapper;

import com.blog.vo.Pager;
import com.blog.vo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签Mapper
 *
 * @author NanCheung
 */
@Mapper
public interface TagMapper {
    /**
     * 获取标签的数量
     *
     * @return
     */
    int getTagCount();
    
    /**
     * 通过id标签
     *
     * @param id
     * @return
     */
    Tag getTagById(Integer id);
    
    /**
     * 分页查询标签列表
     *
     * @param pager   分页对象
     * @param tagName
     * @return
     */
    List<Tag> loadTagList(@Param("pager") Pager pager, @Param("tagName") String tagName);
    
    /**
     * 保存一个标签
     *
     * @param tag
     */
    void saveTag(Tag tag);
    
    /**
     * 检查是否已经存在
     *
     * @param tag
     * @return
     */
    int checkExist(Tag tag);
    
    /**
     * 更新标签
     *
     * @param tag
     */
    void updateTag(Tag tag);
    
    /**
     * @param pager 分页对象
     * @return
     */
    int initPage(Pager pager);
    
    /**
     * 获取所有的标签
     *
     * @return
     */
    List<Tag> getTagList();
    
    /**
     * 加载此tag下的所有文章
     *
     * @param tagId
     * @return
     */
    int articleTagPage(int tagId);
    
    void deleteTagById(int id);
    
    void deleteArticleTagById(int id);
}
