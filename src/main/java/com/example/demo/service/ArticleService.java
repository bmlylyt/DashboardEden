package com.example.demo.service;

import com.example.demo.RPCDomain.req.ArticleRequest;
import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.dao.ArticleDao;
import com.example.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService extends BaseService<Article, String> {

    ResponseResult publish(String userId, ArticleRequest articleRequest);
}
