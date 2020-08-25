package com.example.UserCeneter.dao;

import com.example.UserCeneter.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article, String> {
}
