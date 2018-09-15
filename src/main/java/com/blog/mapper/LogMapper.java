package com.blog.mapper;

import com.blog.vo.LogInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志信息Mapper
 *
 * @author NanCheung
 */
@Mapper
public interface LogMapper {
    /**
     * 保存日志信息
     *
     * @param log 日志对象
     */
    void save(LogInfo log);
}
