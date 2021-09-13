package com.wf.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wf.management.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper extends BaseMapper<Book> {
    // 根据用户id查询图书信息
    List<Book> findByUserId(@Param("userId") Integer userId);
}
