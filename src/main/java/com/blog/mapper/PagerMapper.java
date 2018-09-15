package com.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 分页mapper
 *
 * @author NanCheung
 */
@Mapper
public interface PagerMapper {
    /**
     * 找到目标分类下的正常文章
     *
     * @param categoryId 分类ID
     * @return 目标分类下的正常文章
     */
    int loadCategoryPager(Integer categoryId);
    
    /**
     * 获取标签下正常文章总数量
     *
     * @param tagId 标签ID
     * @return 此标签下正常文章总数量
     */
    int loadTagPager(Integer tagId);
    
    /**
     * 初始化时间归档的分页信息
     *
     * @param createTime 创建时间
     * @return 此时间归档的文章总数
     */
    int loadArchivePager(String createTime);
}
