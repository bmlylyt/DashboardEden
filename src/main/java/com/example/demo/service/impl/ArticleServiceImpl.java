package com.example.demo.service.impl;

import com.example.demo.RPCDomain.req.ArticleRequest;
import com.example.demo.RPCDomain.response.ArticleResponse;
import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.common.ResultCode;
import com.example.demo.common.utils.UUIDUtils;
import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.ArticleTagDao;
import com.example.demo.model.Article;
import com.example.demo.model.ArticleTag;
import com.example.demo.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, String>
        implements ArticleService {

    @Autowired
    private ArticleTagDao articleTagDao;

    @Autowired
    private ArticleDao articleDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.articleTagDao;
    }

    @Override
    public ResponseResult publish(String userId, ArticleRequest articleRequest) {
        Article article = new Article();

        BeanUtils.copyProperties(articleRequest, article);
        article.setId(UUIDUtils.get());
        article.setUserId(userId);
        article.setPublishTime(new Timestamp(articleRequest.getPublishTime().getTime()));

        ArticleTag articleTag = new ArticleTag();
        for (String tag : articleRequest.getArticleTagList()) {
            articleTag.setTagName(tag);
            articleTag.setId(UUIDUtils.get());
            articleTag.setArticleId(article.getId());
            articleTagDao.save(articleTag);
        }
        articleDao.save(article);
        return new ResponseResult(ResultCode.SUCCESS, article.getId());
    }

    @Override
    public ResponseResult updateArticleByAuthor(String userId, ArticleRequest articleRequest) {
        Article article = new Article();
        BeanUtils.copyProperties(articleRequest, article);
        article.setPublishTime(new Timestamp(articleRequest.getPublishTime().getTime()));
        article.setUserId(userId);
        articleTagDao.deleteAllTagByArticleId(articleRequest.getId());

        if (articleRequest.getArticleTagList() != null) {
            ArticleTag articleTag;
            for (String tag : articleRequest.getArticleTagList()) {
                articleTag = new ArticleTag();
                articleTag.setTagName(tag);
                articleTag.setId(UUIDUtils.get());
                articleTag.setArticleId(article.getId());
                articleTagDao.save(articleTag);
            }
        }
        articleDao.save(article);
        return new ResponseResult(ResultCode.SUCCESS);
    }

    @Override
    public ResponseResult deleteArticleById(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResponseResult(ResultCode.PARAM_IS_BLANK, "The article id cannot be empty");
        } else if (!articleDao.findById(id).isPresent()){
            return new ResponseResult(ResultCode.RESULT_DATA_NONE, "The article doesn't exist");
        }
        articleDao.deleteById(id);
        articleTagDao.deleteAllTagByArticleId(id);
        return new ResponseResult(ResultCode.SUCCESS);
    }

    @Override
    public ResponseResult showSingleArticle(String id) {
        if (StringUtils.isBlank(id)) {
            return new ResponseResult(ResultCode.PARAM_IS_BLANK, "The Article id cannot be empty");
        }
        Optional<Article> articleOptional = articleDao.findById(id);
        if (articleOptional.isPresent()) {
            List<String> articalTags = articleTagDao.findTagNamesByArticleId(id);
            ArticleResponse articleResponse = new ArticleResponse();
            BeanUtils.copyProperties(articleOptional.get(), articleResponse);
            articleResponse.setArticleTagList(articalTags);
            return new ResponseResult(ResultCode.SUCCESS, articleResponse);
        }
        return new ResponseResult(ResultCode.RESULT_DATA_NONE);
    }

    @Override
    public List<Article> getRecentArticles() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "publishTime"));
        ArrayList<Article> articles = new ArrayList<>();
        articleDao.findAll(pageable).forEach(articles::add);
        return articles;
    }
}
