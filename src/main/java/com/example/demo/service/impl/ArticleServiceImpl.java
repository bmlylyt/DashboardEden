package com.example.demo.service.impl;

import com.example.demo.RPCDomain.req.ArticleRequest;
import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.common.ResultCode;
import com.example.demo.common.utils.UUIDUtils;
import com.example.demo.dao.ArticleTagDao;
import com.example.demo.model.Article;
import com.example.demo.model.ArticleTag;
import com.example.demo.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, String>
        implements ArticleService {

    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.articleTagDao;
    }

    @Override
    public ResponseResult publish(String userId, ArticleRequest articleRequest) {
        Article article = new Article();

        BeanUtils.copyProperties(articleRequest, article);
        article.setId(UUIDUtils.get());
        article.setPublishTime(new Timestamp(articleRequest.getPublishTime().getTime()));

        ArticleTag articleTag = new ArticleTag();
        for (String tag : articleRequest.getArticleTagList()) {
            articleTag.setTagName(tag);
            articleTag.setId(UUIDUtils.get());
            articleTag.setArticleId(article.getId());
            articleTagDao.save(articleTag);
        }
        return new ResponseResult(ResultCode.SUCCESS, article.getId());
    }
}
