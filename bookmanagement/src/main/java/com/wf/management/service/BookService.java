package com.wf.management.service;

import com.wf.management.entity.Book;
import com.wf.management.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能描述
 *
 * @author: wf
 **/
@Component
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public Book selectById(int id) {
        return bookMapper.selectById(id);
    }
}
