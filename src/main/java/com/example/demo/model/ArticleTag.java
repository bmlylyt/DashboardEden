package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author by plain yuan
 * @since 2020/04/12
 * 文章标签实体类
 */
@Entity
@Table(name = "article_tag")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = -3786101565866282470L;
    @Id
    @Column(name = "id", length = 128, nullable = false)
    private String id;

    @Column(name = "article_id", nullable = false, length = 48)
    private String articleId;

    @Column(name = "tag_name", length = 1024, nullable = false)
    private String tagName;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

