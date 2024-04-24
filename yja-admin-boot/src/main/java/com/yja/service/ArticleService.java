package com.yja.service;

import com.yja.pojo.Article;
import com.yja.pojo.PageBean;

public interface ArticleService {
    void add(Article article);
    // 条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
