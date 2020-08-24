package com.example.demo.service.impl;

import com.example.demo.RPCDomain.req.ArticleRequest;
import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.dao.ArticleTagDao;
import com.example.demo.model.ArticleTag;
import com.example.demo.service.ArticleService;
import com.example.demo.service.ArticleTagService;
import com.example.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class ArticleTagServiceImpl extends BaseServiceImpl<ArticleTag, String>
                                    implements ArticleTagService {

    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return articleTagDao;
    }
}
