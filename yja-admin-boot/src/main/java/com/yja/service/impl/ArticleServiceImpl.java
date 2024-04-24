package com.yja.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yja.mapper.ArticleMapper;
import com.yja.pojo.Article;
import com.yja.pojo.PageBean;
import com.yja.service.ArticleService;
import com.yja.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 创建PageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        // 开启分页
        PageHelper.startPage(pageNum,pageSize);
        // 调用mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> articles = articleMapper.list(userId,categoryId,state);
        Page<Article> pb = (Page<Article>) articles;

        // 把数据填充到pageBean
        pageBean.setItems(pb.getResult());
        pageBean.setTotal(pb.getTotal());
        return pageBean;
    }
}
