package com.example.UserCeneter.controller;

import com.example.UserCeneter.RPCDomain.req.ArticleRequest;
import com.example.UserCeneter.RPCDomain.response.ResponseResult;
import com.example.UserCeneter.common.utils.JwtTokenUtil;
import com.example.UserCeneter.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
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
