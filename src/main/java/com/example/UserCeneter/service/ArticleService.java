package com.example.UserCeneter.service;

import com.example.UserCeneter.RPCDomain.req.ArticleRequest;
import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService extends BaseService<Article, String> {

    ResponseResult publish(String userId, ArticleRequest articleRequest);

    ResponseResult updateArticleByAuthor(String userId, ArticleRequest articleRequest);

    ResponseResult deleteArticleById(String id);

    ResponseResult showSingleArticle(String id);

    List<Article> getRecentArticles();
}
