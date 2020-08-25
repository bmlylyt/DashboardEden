package com.example.UserCeneter.service.impl;

import com.example.UserCeneter.dao.ArticleTagDao;
import com.example.UserCeneter.model.ArticleTag;
import com.example.UserCeneter.service.ArticleTagService;
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
