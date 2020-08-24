package com.example.demo.controller;

import com.example.demo.RPCDomain.req.ArticleRequest;
import com.example.demo.RPCDomain.response.ResponseResult;
import com.example.demo.common.utils.JwtTokenUtil;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "publish", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult publish(@RequestHeader(name= JwtTokenUtil.AUTH_HEADER_KEY) String authHeader,
                                  @RequestBody ArticleRequest articleRequest) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(authHeader);
        return articleService.publish(userId, articleRequest);
    }

    @PostMapping(value = "update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult update(@RequestHeader(name= JwtTokenUtil.AUTH_HEADER_KEY) String authHeader,
                                  @RequestBody ArticleRequest articleRequest) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(authHeader);
        return articleService.updateArticleByAuthor(userId, articleRequest);
    }

    @RequestMapping(value = "delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult delete(@RequestBody ArticleRequest articleRequest) {
        return articleService.deleteArticleById(articleRequest.getId());
    }

    @RequestMapping(value = "show/detail", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseResult showDetail(@RequestBody ArticleRequest articleRequest) {
        return articleService.showSingleArticle(articleRequest.getId());
    }
}